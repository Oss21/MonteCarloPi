package main;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;

import Thread.ThreadData;
import services.ServiceServer;
import services.ServiceBroker;

public class ServerImp implements ServiceServer, Runnable {

	@Property
	private String ruta;

	@Reference
	private ServiceBroker serviceBroker;

	private ThreadData thread;

	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}

	@Override
	public void run() {
		System.out.println("Servidor esta corriendo");
		serviceBroker.darRutaServer(ruta);		
	}

	@Override
	public double[] pedirPuntos(int semilla, double cantNumeros) {
		return null;
	}

}