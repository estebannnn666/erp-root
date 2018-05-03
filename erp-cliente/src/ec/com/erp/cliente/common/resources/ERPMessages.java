package ec.com.erp.cliente.common.resources;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Gestor del archivo de propiedades interno de la Aplicacion
 * 
 * @author Esteban Gudino
 */

public class ERPMessages {

	private final static String AMBIENTE = "CONSOLE";
	private final static String BUNDLE_NAME = "ec.com.erp.cliente.common.resources.ERPAplicacion"; //$NON-NLS-1$
	private static ResourceBundle resourceBundle = null;
	private static Locale locale = new Locale("");

	static {
		String env = AMBIENTE;
		if (env.equals(AMBIENTE)) {

			if (isUnix()) {
				locale = new Locale("con_unix");
			} else
				locale = new Locale("con");

		} else if (env.equals(AMBIENTE)) {

			if (isUnix()) {
				locale = new Locale("des_unix");
			} else
				locale = new Locale("des");

		} else if (env.equals(AMBIENTE)) {
			if (isUnix()) {
				locale = new Locale("pru_unix");
			} else
				locale = new Locale("pru");
		} else if (env.equals(AMBIENTE)) {
			if (isUnix()) {
				locale = new Locale("pro_unix");
			} else
				locale = new Locale("pro");
		}


		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);

	}

	/**
	 * Constructor port defecto
	 */
	public ERPMessages() {
		super();
	}

	/**
	 * Recupera una valor del archivo de propiedades dado la clave
	 * 
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Recupera una valor entero del archivo de propiedades dado la clave
	 * 
	 * @param key
	 * @return Integer
	 */
	public static Integer getInteger(String key) {
		try {
			return Integer.valueOf(resourceBundle.getString(key));
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Recupera una valor long del archivo de propiedades dado la clave
	 * 
	 * @param key
	 * @return Integer
	 */
	public static Long getLong(String key) {
		try {
			return Long.valueOf(resourceBundle.getString(key));
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	/**
	 * Recupera una valor bigdecimal del archivo de propiedades dado la clave
	 * 
	 * @param key
	 * @return Integer
	 */
	public static BigDecimal getBigDecimal(String key) {
		try {
			return BigDecimal.valueOf(Long.valueOf(resourceBundle.getString(key)));
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Identifica si el sistema operativo es Unix
	 * 
	 * @return Boolean
	 */
	public static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0);
	}
}
