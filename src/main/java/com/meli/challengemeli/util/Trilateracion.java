package com.meli.challengemeli.util;

import java.util.List;

import com.meli.challengemeli.models.Coordenadas;

public class Trilateracion {
	
	/**
	 * Método encargado de encontrar la posición de un punto usando el proceso de Trilateracion
	 * @param coordenadas de los 3 puntos
	 * @param distancias al emisor
	 * @return coordenadas del emisor
	 */
	public static Coordenadas findLocation(List<Coordenadas> coordenadas, List<Double> allDistance) {
		
		Coordenadas p1 =  coordenadas.get(0);
		Coordenadas p2 =  coordenadas.get(1);
		Coordenadas p3 =  coordenadas.get(2);
		
		var temp = (p2.getX() - p1.getX())*(p2.getX() - p1.getX());
		temp += (p2.getY() - p1.getY())*(p2.getY() - p1.getY());		
		double d = Math.sqrt(temp);
		
		var p2p1X = (p2.getX() - p1.getX())*(p2.getX() - p1.getX())/d;
		var p2p1Y = (p2.getY() - p1.getY())*(p2.getY() - p1.getY())/d;		
		Coordenadas p2p1 = new Coordenadas(p2p1X, p2p1Y);
		
		var p3p1X = (p3.getX() - p1.getX())*(p3.getX() - p1.getX())/d;
		var p3p1Y = (p3.getY() - p1.getY())*(p3.getY() - p1.getY())/d;		
		Coordenadas p3p1 = new Coordenadas(p3p1X, p3p1Y);
		
		temp = (p2p1.getX() * p3p1.getX())*(p2p1.getX() - p3p1.getX());				
		
		var p3p1Factor = p3.getX() - p1.getX() - (p2p1.getX()*temp);
		p3p1Factor += p3.getY() - p1.getY() - (p2p1.getY()*temp);
		
		var p3p1p2p1X = (p3.getX() - p1.getX() - (p2p1.getX()*temp))/Math.sqrt(p3p1Factor);
		var p3p1p2p1Y = (p3.getY() - p1.getY() - (p2p1.getY()*temp))/Math.sqrt(p3p1Factor);
		
		Coordenadas p3p1p2p1 = new Coordenadas(p3p1p2p1X, p3p1p2p1Y);
		
		var factor = p3p1p2p1.getX()*p3p1.getX();
		factor += p3p1p2p1.getY()*p3p1.getY();
		
		var xval = (Math.pow(allDistance.get(0), 2) - Math.pow(allDistance.get(1), 2) + Math.pow(d, 2))/(2*d);
		var yval = ((Math.pow(allDistance.get(0), 2) - Math.pow(allDistance.get(2), 2) + Math.pow(temp, 2) + Math.pow(factor, 2))/(2*factor)) - ((temp/factor)*xval);			
		
		return new Coordenadas(xval,yval);
	}
	
}
