package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceBroker {
	void darRutaServer(String ruta);
	double[] darPuntos(int semilla, double cantidad);
}
