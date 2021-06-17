package main;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	// @Reference
	private static ArrayList<ServiceServer> servers = new ArrayList<ServiceServer>();
	private final static double TAMAHNO_BLOQUE = 10000000;

	private double puntosDentroCirculo = 0.0;
	private double puntosDentroCuadrado = 0.0;
	private ThreadData threadData;

	/**
	 * Permite calcular el total de puntos generado por los diferentes servidores.
	 *
	 * @return retorna el número de puntos en el circulo y el número de puntos
	 *         fuera del el.
	 */
	@Override
	public double[] darPuntos(long semilla, double cantidad) {

		// bloques = 10100/10000 = hilos 1 bloques
		// nPoints = tb > cantidad ? cantidad: tb;
		// cantidad -= nPoints;
		int hilos = 0;
		Random seed = new Random(semilla);

		while (cantidad > 0) {
			ArrayList<ThreadData> threads = new ArrayList<ThreadData>();

			// Start
			ExecutorService executor = Executors.newFixedThreadPool(servers.size());
			for (final ServiceServer s : servers) {
				if (cantidad > 0) {
					double nPoints = TAMAHNO_BLOQUE > cantidad ? cantidad : TAMAHNO_BLOQUE;
					// crearNSubProcesos(s, seed, tamahnoProcesos, TAMAHNO_BLOQUE, threads);
					crearNSubProcesos(s, seed, TAMAHNO_BLOQUE, threads);
					// Se le pasa los hilos que se desean ejecutar.
					executor.execute(threadData);
					cantidad -= nPoints;
				} else {
					break;
				}
			}
			executor.shutdown();
			while (!executor.isTerminated())
				;
			long tiempoEjecucionTotal = 0;
			for (ThreadData t : threads) {
				puntosDentroCirculo += t.getPuntosDentroCirculo();
				puntosDentroCuadrado += t.getPuntosDentroCuadrado();
				tiempoEjecucionTotal += t.getTiempoEjecucion();
			}
			// finish
			hilos = threads.size();
			System.out.println("Time de ejecuci�n total " + tiempoEjecucionTotal + " de " + servers.size()+" " +threads.size());
		}

		double[] output = { puntosDentroCirculo, puntosDentroCuadrado, hilos };
		System.out.println("");
		return output;
	}

	private void crearNSubProcesos(ServiceServer server, Random seed, double blocksize, ArrayList<ThreadData> threads) {
		double tamahnoProcesos = TAMAHNO_BLOQUE / 4;
		for (int i = 0; i < 4; i++) {
			threadData = new ThreadData(server, seed.nextLong(), tamahnoProcesos);
			threads.add(threadData);
		}
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
		System.out.println("***************************");
		System.out.println("Se ha levantado el broker");
		System.out.println("***************************");
		System.out.println();
		while (true) {
			try {
				Thread.sleep(5000);
				System.out.println("***************************");
				System.out.println("Cantidad_de_Servidores-------> " + servers.size());
				System.out.println("***************************");
				System.out.println("");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
