package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceServer {
	 String pedirPuntos(int semilla, double cantNumeros);
}
