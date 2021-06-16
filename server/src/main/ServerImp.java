package main;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;

import services.ServiceServer;
import services.ServiceBroker;

public class ServerImp implements ServiceServer, Runnable {

	@Property
	private String ruta;

	@Reference
	private ServiceBroker serviceBroker;

	private Model model;

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	@Init
	public void initilized() {
		model = new Model();
	}
	
	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}
	/**
	 * Este metodo permite pasarle la ruta al cliente donde se encuentra el servidors
	 */
	@Override
	public void run() {
		System.out.println("Servidor esta corriendo");
		serviceBroker.attach(ruta);	
	}

	@Override
	public double[] pedirPuntos(int semilla, double cantNumeros) {
		model.calculateMethodMontecarlo(semilla, cantNumeros);
		double[] output = {model.getInsideCircle(),model.getInsideSquare()};
		return output;
	}

}