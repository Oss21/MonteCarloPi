package thread;

import model.Montecarlo;

public class ThreadData extends Thread {
	private Montecarlo mont;
	private int seed;
	private double start;
	private double end;
	
	
	public ThreadData(String name) {
		super(name);
		mont = new Montecarlo();
	}

	public void run() {
		mont.calculateMethodMontecarloTrhead(seed,start,end);
		System.out.println("Termina thread " + getName());
	}

	public void values(int seed,double start ,double end) {
		this.seed = seed;
		this.start = start;
		this.end = end;
	}
}
