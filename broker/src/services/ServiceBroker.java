package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceBroker {
	void servidores(String ruta);
	String darPuntos(double puntosCirculo, double puntosCuadrado);
}
