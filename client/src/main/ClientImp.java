package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.osoa.sca.annotations.Reference;

import services.ServiceBroker;
import services.ServiceClient;

public class ClientImp implements Runnable, ServiceClient {

	private static GUI gui;
	
	@Reference
	private ServiceBroker serviceBroker;

	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}

	@Override
	public void run() {
		System.out.println("El cliente esta corriendo");
		
		try {
			if(gui == null) {
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
		return 4*(puntosCirculo/puntosCuadrado);
	}	
	
    private void configureEvents() {
		gui.getButCalcPi().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					long semilla = gui.getSemilla();
					final double numPuntos = gui.getNumPuntos();
					
					//double[] values = serviceBroker.darPuntos(12, 1000000000);
					double[] values = serviceBroker.darPuntos(semilla, numPuntos);
					calcularPi(values[0], values[1]);
					
					//gui.setLabValPi(numPuntos*semilla);
				}
			}
		);
	}

}
