package main;

import java.util.Random;

public class Model {

	private double insideCircle;
	public double getInsideCircle() {
		return insideCircle;
	}

	

	private double insideSquare;

	public void calculateMethodMontecarlo(int seed, long size) {
		Random random = new Random(seed);
		double pi = 0;
		for (int i = 0; i < size; i++) {
			calculateCoordenateSquare(random);
		}
		pi = 4*(insideCircle/insideSquare);
		System.out.println(insideCircle);
		System.out.println(pi);
	}

	public void calculateMethodMontecarloTrhead(int seed,double start ,double end) {
		Random random = new Random(seed);
		double pi = 0;
		for (double i = start; i <= end; i++) {
			calculateCoordenateSquare(random);
			//System.out.println(i+" "+"pi: "+pi);
		}
		pi = 4*(insideCircle/insideSquare);
		System.out.println("pi: "+pi);

	}

	/**
	 * Este metodo permite calcular las coordenadas para el cuadrado.
	 * 
	 * @param random
	 */
	private void calculateCoordenateSquare(Random random) {
		double xCoordSquare = random.nextDouble();
		double yCoordSquare = random.nextDouble();
		
		insideCircle += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 0;
		insideSquare += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 1;

		insideCircle += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 0;
		insideSquare += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 1;

		//System.out.println("Square " + xCoordSquare + " : " + yCoordSquare);
	}
	
	public void setInsideCircle(double insideCircle) {
		this.insideCircle = insideCircle;
	}

	public double getInsideSquare() {
		return insideSquare;
	}

	public void setInsideSquare(double insideSquare) {
		this.insideSquare = insideSquare;
	}
	
	
}