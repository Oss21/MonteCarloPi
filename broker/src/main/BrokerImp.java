package main;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.osoa.sca.annotations.Reference;

import Thread.ThreadData;
import services.ServiceBroker;
import services.ServiceServer;

public class BrokerImp implements ServiceBroker, Runnable {

	/**
	 * Correponde a la interfaz que provee la clase ServerImp
	 */
	// @Reference
	// private ServiceServer serviceServer;
	// private int numeroDeServers;
	/*
	 * Numero de servidores corriendo en el sistema
	 */
	@Reference
	private ArrayList<ServiceServer> servers;
	private final static double TAMAHNO_BLOQUE = 1.000000000; 
	
	private double puntosDentroCirculo = 0.0;
	private double puntosDentroCuadrado = 0.0;
	
	
	private ThreadData threadData;

	/**
	 * Permite calcular el total de puntos generado por los diferentes servidores.
	 * 
	 * @return retorna el número de puntos en el circulo y el número de puntos fuera
	 *         del el.
	 */
	@Override
	public double[] darPuntos(long semilla, double cantidad) {

		int bloques = (int) (cantidad / TAMAHNO_BLOQUE);// 20
		// bloques = 100/100 = hilos 1 bloques

		Random seed = new Random(semilla);
		int rep = bloques / servers.size();
		do {
			ArrayList<ThreadData> threads = new ArrayList<ThreadData>();
			// Start
			ExecutorService executor = Executors.newFixedThreadPool(servers.size());
			for (final ServiceServer s : servers) {
				threadData = new ThreadData(seed.nextLong(), TAMAHNO_BLOQUE);
				threads.add(threadData);
				// Se le pasa los hilos que se desean ejecutar.
				executor.execute(threadData);
			}
			executor.shutdown();
			while (!executor.isTerminated());

			for (ThreadData t : threads) {
				puntosDentroCirculo += t.getPuntosDentroCirculo();
				puntosDentroCuadrado += t.getPuntosDentroCuadrado();
			}
			// finish
		} while (rep-- > 0);

		double[] output = { puntosDentroCirculo, puntosDentroCuadrado };

		return output;
	}

	/***
	 * Este metodo permite obtener la ruta en donde se encuentra alojado el
	 * servidor. De esta manera, poder enviarle las peticiones pertinentes
	 * 
	 * @param ruta: Representa la ruta en donde se encuentra alojado el servidor
	 */
	@Override
	public void attach(String ruta) {
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
