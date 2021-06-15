package main;

import org.osoa.sca.annotations.Reference;

import services.ServiceBroker;
import services.ServiceClient;

public class ClientImp implements Runnable, ServiceClient {

	@Reference
	private ServiceBroker serviceBroker;

	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}

	@Override
	public void run() {
		System.out.println("El cliente esta corriendo");
	}

	@Override
	public double calcularPi(double puntosCirculo, double puntosCuadrado) {
		return 4*(puntosCirculo/puntosCuadrado);
	}
	
	
	
	
	
	

}
