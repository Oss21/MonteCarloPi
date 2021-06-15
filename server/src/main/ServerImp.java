package main;

import org.osoa.sca.annotations.Reference;

import Thread.ThreadData;
import services.ServiceServer;
import services.ServiceBroker;
public class ServerImp implements ServiceServer, Runnable{

	private ThreadData thread;
	
	
	@Reference
	private ServiceBroker serviceBroker;
	
	
	public void setServiceBroker(ServiceBroker serviceBroker) {
		this.serviceBroker = serviceBroker;
	}
	
	
	@Override
	public void run() {
		System.out.println("Servidor esta corriendo");
	}

	@Override
	public String pedirPuntos(int semilla, double cantNumeros) {
		
		
		
		
		
		
		
		
		
		return null;
	}

	

	





    

}