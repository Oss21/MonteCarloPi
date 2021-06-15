package services;

import org.osoa.sca.annotations.Service;

@Service
public interface Servicebroker {

	String resivirPuntos(double puntosCirculo, double puntosCuadrado);
}
