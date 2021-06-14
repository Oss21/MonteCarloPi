package model;

import java.util.Random;

public class Montecarlo {

	private double xCoordSquare;
	private double yCoordSquare;
	private double xCoordCircle;
	private double yCoordCircle;
	private double insideCircle;
	private double insideSquare;

	public void calculateMethodMontecarlo(int seed, long size) {
		Random random = new Random(seed);
		double pi = 0;
		for (int i = 0; i < size; i++) {
			calculateCoordenateSquare(random);
			calculateCoordenateCircle();
			isInsideTheCircle();
			isInsideTheSquared();
		}
		pi = 4*(insideCircle/insideSquare);
		System.out.println("pi: "+pi);
	}

	public void calculateMethodMontecarloTrhead(int seed,double start ,double end) {
		Random random = new Random(seed);
		double pi = 0;
		for (double i = start; i <= end; i++) {
			calculateCoordenateSquare(random);
			calculateCoordenateCircle();
			isInsideTheCircle();
			isInsideTheSquared();
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
		xCoordSquare = random.nextDouble();
		yCoordSquare = random.nextDouble();
		
		//System.out.println("Square " + xCoordSquare + " : " + yCoordSquare);
	}

	/**
	 * Este metodo permite calcular las coordenadas para el circulo.
	 * 
	 */
	private void calculateCoordenateCircle() {
		double result = Math.pow(xCoordSquare, 2) + Math.pow(yCoordSquare, 2);
		xCoordCircle = (result) <= 1? xCoordSquare : 0;
		yCoordCircle = (result) <= 1? yCoordSquare : 0;		
	    //System.out.println("Circle " + xCoordSquare + " : " + yCoordSquare);
	}

	private void isInsideTheCircle() {
		insideCircle += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 0;
		//System.out.println(insideCircle);
	}

	private void isInsideTheSquared() {
		insideSquare += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 1;
		//System.out.println(insideSquare);

	}
	
	
	
	
	

}
