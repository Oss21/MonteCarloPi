package main;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.osoa.sca.annotations.Reference;
import services.ServiceBroker;
import services.ServiceServer;

public class BrokerImp implements ServiceBroker, Runnable {

	/**
	 * Correponde a la interfaz que provee la clase ServerImp
	 */
	@Reference
	private ServiceServer serviceServer;
	// private int numeroDeServers;
	/*
	 * Numero de servidores corriendo en el sistema
	 */
	private ArrayList<ServiceServer> servers;
	
	
	private double puntosDentroCirculo = 0.0;
	private double puntosDentroCuadrado = 0.0;

	/**
	 * Permite inyectar la interfaz que provee la clase ServerImp
	 * 
	 * @param serviceServer Interfaz que provee la clase ServerImp
	 */
	public void setServiceBroker(ServiceServer serviceServer) {
		this.serviceServer = serviceServer;
	}

	
	
	
	/**
	 * Permite calcular el total de puntos generado por los diferentes servidores.
	 * 
	 * @return retorna el número de puntos en el circulo y el número de puntos fuera
	 *         del el.
	 */
	@Override
	public double[] darPuntos(int semilla, double cantidad) {

		double numPuntos = cantidad;
		double tamahnoDeNodo = numPuntos / servers.size();

		ArrayList<Thread> threads = new ArrayList<Thread>();
		ExecutorService executor = Executors.newFixedThreadPool(servers.size());
		for (final ServiceServer s : servers) {
			Thread t = new Thread() {
				public void run() {					
					 double[] result = s.pedirPuntos(semilla, tamahnoDeNodo);
					 puntosDentroCirculo += result[0];
					 puntosDentroCuadrado += result[1];
				}
		
			};
			threads.add(t);
			// Se le pasa los hilos que se desean ejecutar.
			executor.execute(t);
		}
		executor.shutdown();
		while (!executor.isTerminated());
		
		double[] output = {puntosDentroCirculo,puntosDentroCuadrado};

		return output;
	}

	/***
	 * Este metodo permite obtener la ruta en donde se encuentra alojado el
	 * servidor. De esta manera, poder enviarle las peticiones pertinentes
	 * 
	 * @param ruta: Representa la ruta en donde se encuentra alojado el servidor
	 */
	@Override
	public void darRutaServer(String ruta) {
		try {
			ServiceServer s = (ServiceServer) Naming.lookup(ruta);
			servers.add(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("#########################");
		System.out.println("Se ha levantado el broker");
		System.out.println("#########################");
	}

}
