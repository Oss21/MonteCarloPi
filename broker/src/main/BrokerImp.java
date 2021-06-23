package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
	private final static double TAMAHNO_BLOQUE = 10000000.0;

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
		int subProcesos = 0;
		while (cantidad > 0) {
			ArrayList<ThreadData> threads = new ArrayList<ThreadData>();

			// Start
			ExecutorService executor = Executors.newFixedThreadPool(servers.size());
			for (final ServiceServer s : servers) {
				if (cantidad > 0) {
					double nPoints = TAMAHNO_BLOQUE > cantidad ? cantidad : TAMAHNO_BLOQUE;
					// crearNSubProcesos(s, seed, tamahnoProcesos, TAMAHNO_BLOQUE, threads);
					subProcesos = aumentarNSubprocesos(cantidad);
					crearSubProcesos(s, seed, TAMAHNO_BLOQUE, threads, subProcesos);
					// Se le pasa los hilos que se desean ejecutar.
					//executor.execute(threadData);
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
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("-----------------------------------------------");
			System.out.println("Time de ejecucionn total " + tiempoEjecucionTotal + " # de nodos: " + servers.size()
					+ " #SubProcesos: " + subProcesos);
			System.out.println("-----------------------------------------------");
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.println("Total hilos procesando en todas las maquinas: " + threads.size());
			System.out.println("-----------------------------------------------");
		}

		double[] output = { puntosDentroCirculo, puntosDentroCuadrado, hilos };
		System.out.println("");
		return output;
	}

	/**
	 * Este metodo permite crear subprocesos dentro de un nodo de procesamiento con
	 * el fin de reducir con el tiempo en que tarda un sistema en responder a los
	 * eventos. Ademas, cumple con el atributo de calidad ya que a medida que
	 * aumenta el número de datos a procesar el programa puede adaptarse y
	 * responder sin perder el rendimiento
	 *
	 * @param server      servidores disponibles para enviar tareas de
	 *                    procesamiento.
	 * @param seed        semilla para crear randoms con la mismas secuencias.
	 * @param blocksize   tamaño de datos a procesar, los cuales seran divididos en
	 *                    pequeñas cantidades para agilizar el procesamiento.
	 * @param threads     hilos que posteriormente se van a ejecutar como
	 *                    subprocesos.
	 * @param subProcesos cantidad de subprocesos a generar
	 */
	private void crearSubProcesos(ServiceServer server, Random seed, double blocksize, ArrayList<ThreadData> threads,
			int subProcesos) {
		double tamahnoProcesos = TAMAHNO_BLOQUE / subProcesos;
		for (int i = 0; i < subProcesos; i++) {
			threadData = new ThreadData(server, seed.nextLong(), tamahnoProcesos);
			threads.add(threadData);
			executor.execute(threadData);
		}
	}

	/***
	 * Este metodo nos permite aumentar la cantidad sub-nodos de procesamiento que
	 * contiene un nodo, conforme a la cantidad de carga que se le transfiera y
	 * disminuir cuando no lo requiera. calculo amerite.
	 *
	 * @param cantidad Numero de datos a procesar
	 * @return cantidad sub-nodos o subprocesos a ejecutar.
	 */
	private int aumentarNSubprocesos(double cantidad) {
		int numeroSubProcesos = 0;
		if (cantidad < 10000000) {
			numeroSubProcesos = 1;
		} else if (cantidad < 100000000) {
			numeroSubProcesos = 4;
		} else if (cantidad < 10000000000.0) {
			numeroSubProcesos = 8;
		} else if (cantidad < 1000000000000.0) {
			numeroSubProcesos = 12;
		} else {
			numeroSubProcesos = 16;
		}
		return numeroSubProcesos;
	}

	/***
	 * Este metodo permite obtener la ruta en donde se encuentra alojado el
	 * servidor. De esta manera, poder enviarle las peticiones pertinentes
	 *
	 * @param ruta: Representa la ruta en donde se encuentra alojado el servidor
	 */
	@Override
	public void attach(String ruta) {
		ServiceServer s = null;
		try {
			s = (ServiceServer) Naming.lookup(ruta);
			servers.add(s);
		} catch (Exception e) {
			try {
				Naming.unbind(ruta);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
			servers.remove(s);
			System.out.println("Se ha caido el nodo: " + s);
			System.out.println("Se ha caido uno de los nodos, pero tranquilo. Pronto, sera reasignado");
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
