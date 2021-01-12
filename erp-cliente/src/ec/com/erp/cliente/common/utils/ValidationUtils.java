package ec.com.erp.cliente.common.utils;

public class ValidationUtils {
	
	public static String obtenerSecuencialFactura(int numTotal, String cadena) {
		StringBuilder cadenaCompleta = new StringBuilder();
		cadenaCompleta.append("001-");
		int tamCadena = cadena.length();
		int cont = tamCadena;
		while(cont < numTotal) {
			cadenaCompleta.append("0");
			cont++;
		}
		cadenaCompleta.append(cadena);
		return cadenaCompleta.toString();
	}
}
