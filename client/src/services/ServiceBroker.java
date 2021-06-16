package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceBroker {
	void attach(String uri);
	double[] darPuntos(long semilla, double cantidad);
}
