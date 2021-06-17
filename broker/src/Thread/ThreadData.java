package Thread;

import services.ServiceServer;

public class ThreadData extends Thread {


	private long seed;
	private double blocksize;
	private ServiceServer server;
	private long tiempoEjecucion;
	

	private double puntosDentroCirculo = 0.0;
	private double puntosDentroCuadrado = 0.0;
	

	public ThreadData(ServiceServer server, long seed, double blocksize) {
		super();
		this.server = server;
 		this.seed = seed;
		this.blocksize = blocksize;
	}

	public void run() {
		long tiempoInicio = System.currentTimeMillis();
		double[] values = server.pedirPuntos(seed, blocksize);
		puntosDentroCirculo = values[0];
		puntosDentroCuadrado = values[1];
		tiempoEjecucion = System.currentTimeMillis() - tiempoInicio;
	}

	public double getPuntosDentroCirculo() {
		return puntosDentroCirculo;
	}

	public double getPuntosDentroCuadrado() {
		return puntosDentroCuadrado;
	}
	
	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}
}
