package model;

import java.util.Iterator;

import thread.ThreadData;

public class Main {

	private static Montecarlo montecarlo;

	public static void main(String[] args) {
		int seed = 1;
		double size = 10000000.0;
		//double size = 1000000000000.0;
		int numberPartition = 8;
		double rangePartition = size / numberPartition;
		long i = 1;
		montecarlo = new Montecarlo();
		/*
		int count = 0;
		ThreadData thread = null;
		long TInicio, TFin, tiempo; // Variables para determinar el tiempo de ejecución
		TInicio = System.currentTimeMillis();
		while (i <= size) {
			thread = new ThreadData(String.valueOf(++count));
			thread.values(seed, i, i + rangePartition);
			thread.start();
			// System.out.println(i+" "+ (i+rangePartition-1));
			i += rangePartition;
		}
		System.out.println(thread.isAlive());
		TFin = System.currentTimeMillis();
		tiempo = TFin - TInicio;
		System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
		// thread.start();
		 */
		montecarlo.calculateMethodMontecarlo(100,100000000);

	}

}
