package Thread;

import services.ServiceBroker;

public class ThreadData extends Thread {

	private long seed;
	private double blocksize;
	private ServiceBroker serviceBroker;

	private double puntosDentroCirculo = 0.0;
	private double puntosDentroCuadrado = 0.0;

	public ThreadData(long seed, double blocksize) {
		super();
		this.seed = seed;
		this.blocksize = blocksize;
	}

	public void run() {
		double[] values = serviceBroker.darPuntos(seed, blocksize);
		puntosDentroCirculo = values[0];
		puntosDentroCuadrado = values[1];
		System.out.println("Termina thread ");
	}

	public double getPuntosDentroCirculo() {
		return puntosDentroCirculo;
	}

	public double getPuntosDentroCuadrado() {
		return puntosDentroCuadrado;
	}
}
