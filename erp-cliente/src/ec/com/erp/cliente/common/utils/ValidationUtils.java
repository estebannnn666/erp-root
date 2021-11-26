package ec.com.erp.cliente.common.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
	
	/**
	 * Metedo para redondear valores bigdecimal a la scala enviada
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal redondear(BigDecimal value, int scale) {
		int precision = value.scale();
		if(precision <= scale) {
			precision = value.precision();
		}
		MathContext ctx = new MathContext(precision);
		return value.round(ctx).setScale(scale, RoundingMode.HALF_UP);
	}
	
	public static String obtenerSecuencialNotaCredito(int numTotal, String establecimiento, String puntoEmision, String cadena) {
		StringBuilder cadenaCompleta = new StringBuilder();
		cadenaCompleta.append(establecimiento).append("-").append(puntoEmision).append("-");
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
