package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceBroker {
	String darPuntos(double puntosCirculo, double puntosCuadrado);
}
