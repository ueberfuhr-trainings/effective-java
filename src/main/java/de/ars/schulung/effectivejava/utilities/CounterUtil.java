package de.ars.schulung.effectivejava.utilities;

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

}
