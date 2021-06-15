package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceBroker {

	String resivirPuntos(double puntosCirculo, double puntosCuadrado);
}
