package de.ars.schulung.effectivejava.utilities;

import java.lang.reflect.Field;

/**
 * Hilfsklasse für eine Zählvariable
 */
final class CounterUtil {

	private static int index = 1;

	private CounterUtil() {
	}

	public static synchronized int index() {
		return index++;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static {
		try {
			Integer a = 87;
			Field valField = a.getClass().getDeclaredField("value");
			valField.setAccessible(true);
			valField.set(a, 86);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
