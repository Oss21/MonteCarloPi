package model;

public class Main {
	
	private static Montecarlo montecarlo;
	
	public static void main(String[] args) {
		montecarlo = new Montecarlo();
		montecarlo.calculateMethodMontecarlo(100,999999);
		
	}

}
