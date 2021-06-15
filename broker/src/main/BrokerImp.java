package main;

import org.osoa.sca.annotations.Reference;

import services.ServiceBroker;
import services.ServiceServer;

public class BrokerImp implements ServiceBroker {

	
	@Reference
	private ServiceServer serviceServer;
	
	public void setServiceBroker(ServiceServer serviceServer) {
		this.serviceServer = serviceServer;
	}
	
	
	@Override
	public String darPuntos(double puntosCirculo, double puntosCuadrado) {
		return null;
	}


	@Override
	public void servidores(String ruta) {
		
	}

}
