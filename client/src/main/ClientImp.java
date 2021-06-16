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
		double[] values = serviceBroker.darPuntos(12, 10000000000);
		calcularPi(values[0], values[1]);
	}
		
	@Override
	public double calcularPi(double puntosCirculo, double puntosCuadrado) {
		return 4*(puntosCirculo/puntosCuadrado);
	}
	
	
	
	
	
	

}
