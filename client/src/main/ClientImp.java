package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.osoa.sca.annotations.Reference;

import services.ServiceBroker;
import services.ServiceClient;

public class ClientImp implements Runnable, ServiceClient {

	private static GUI gui;
	
	private static ArrayList<String[]> datos;
	
	private static String ruta = "./data/";

	@Reference
	private ServiceBroker serviceBroker;

	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}

	@Override
	public void run() {
		System.out.println("El cliente esta corriendo");
		try {
			if (gui == null) {
				gui = new GUI();
				gui.setVisible(true);
				configureEvents();
			}
		} catch (Exception e) {
			System.out.println("Se produjo una excepcion al configurar la interfaz grafica");
			e.printStackTrace();
		}
	}

	@Override
	public double calcularPi(double puntosCirculo, double puntosCuadrado) {
		return 4 * (puntosCirculo / puntosCuadrado);
	}
	
	public void cargarDatos(){
		
		datos = new ArrayList<String[]>();
		
		String[] arreglo = null;
		String line = "";
		
		try {
			FileReader reader = new FileReader(ruta + "test_easy.txt"); //cambiar por test
			BufferedReader br = new BufferedReader(reader);
			
			while((line = br.readLine()) != null){
				arreglo = line.split(",");
				datos.add(arreglo);			
			}
			br.close();
		} catch (Exception exc) {
			
		}			
	}

	private void configureEvents() {
		gui.getButCalcPi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long semilla = gui.getSemilla();
				final double numPuntos = gui.getNumPuntos();

				// double[] values = serviceBroker.darPuntos(12, 1000000000);
				double[] values = serviceBroker.darPuntos(semilla, numPuntos);
				double pi = calcularPi(values[0], values[1]);
				gui.setLabValPi(pi);
			}
		});
		
		gui.getButCargarPrueba().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//desabilito el boton que permite agregar solicitudes individuales
				gui.getButCalcPi().setEnabled(false);
				
				int	rep = 10; //cantidad de veces que se va a realizar cada configuracion
				cargarDatos(); 
				int k = datos.size(); // num de configuraciones
				
				long demoraTotal = 0;
				
				// para guardar los resultados
				try {
					FileWriter writer = new FileWriter(ruta + "resultado.txt");
					BufferedWriter bw = new BufferedWriter(writer);
					
					for (int i = 0; i < k; i++) {
						String[] configuracion = datos.get(i);
						
						bw.write("Semilla: " + configuracion[0] + ", Cantidad de puntos: " + configuracion[1] + "\n");
						bw.write( "\n");
						
						for (int j = 0; j < rep; j++) {
							
							long timeStart = System.currentTimeMillis();
							
							double[] values = serviceBroker.darPuntos(Long.parseLong(configuracion[0]), Double.parseDouble(configuracion[1]));
							double pi = calcularPi(values[0], values[1]);
							
							long timeEnd = System.currentTimeMillis();
							
							long demora = timeEnd - timeStart;
							
							demoraTotal += demora;
							
							bw.write("pi = " + pi + ", Demoro: " + demora + ", nodos de procesamiento: " + values[2] + "\n");
						}							
						bw.write("El promedio para esta configuracion fue: " + (demoraTotal/rep) + "\n" );
						bw.write( "\n");
						demoraTotal = 0;
					}
					
					bw.close();
				} catch (Exception exc) {
					
				}
				gui.getButCalcPi().setEnabled(true);	
			}
		});
	}

}
