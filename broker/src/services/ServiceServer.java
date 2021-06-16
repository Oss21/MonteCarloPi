package services;

import org.osoa.sca.annotations.Service;

@Service
public interface ServiceServer{

    /**
     * calcular y retornar la cantidad de puntos en el cuadrado y la cantidad de puntos dentro del circulo
     * @param cantNumeros cantidad de puntos a calcular
     * @return un string separado por comas con el resultado
     */
	double[] pedirPuntos(long semilla, double cantNumeros);
}
