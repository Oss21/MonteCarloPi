package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceClient {

    double calcularPi(double puntosCirculo, double puntosCuadrado);
   
}
