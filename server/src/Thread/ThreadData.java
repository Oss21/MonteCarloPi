package Thread;

import main.Model;

public class ThreadData extends Thread {
	private Model model;
	private int seed;
	private double start;
	private double end;
	
	
	public ThreadData(String name) {
		super(name);
		model = new Model();
	}

	public void run() {
		model.calculateMethodMontecarloTrhead(seed,start,end);
		System.out.println("Termina thread " + getName());
	}

	public void values(int seed,double start ,double end) {
		this.seed = seed;
		this.start = start;
		this.end = end;
	}
}
