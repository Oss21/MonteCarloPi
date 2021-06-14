package main;

import java.util.Random;

public class Model{

    private double xCoordSquare;
    private double yCoordSquare;
    private double xCoordCircle;
    private double yCoordCircle;
    private double insideCircle;
    private double insideSquare;

    public Model (){

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

    /**
     * decide si esta dentro del circulo
     */
    private void isInsideTheCircle() {
        insideCircle += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 0;
        //System.out.println(insideCircle);
    }

    /**
     * decide si esta dentro del cuadrado
     */
    private void isInsideTheSquared() {
        insideSquare += (Math.pow(xCoordSquare,2) + Math.pow(yCoordSquare,2)) <= 1 ? 1 : 1;
        //System.out.println(insideSquare);
    }
}