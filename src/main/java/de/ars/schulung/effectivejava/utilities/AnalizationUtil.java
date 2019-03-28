package de.ars.schulung.effectivejava.utilities;

import static de.ars.schulung.effectivejava.utilities.CounterUtil.index;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Hilfsklasse für die Ausgabe auf Konsole
 */
public final class AnalizationUtil {

	private AnalizationUtil() {
	}

	/**
	 * Gibt eine Zahl hexadezimal aus.
	 * 
	 * @param s
	 *            die Zahl (Lambda)
	 */
	public static void printHex(Supplier<Number> s) {
		System.out.println(Long.toHexString(Optional.ofNullable(s.get()).orElse(0L).longValue()));
	}

	/**
	 * Gibt eine Nachricht, über einen laufenden Index identifizierbar, auf
	 * Konsole aus.
	 * 
	 * @param message
	 *            die Nachricht (Lambda)
	 */
	public static void printMessage(Supplier<?> message) {
		Object messageResult = message.get(); // zuerst ermitteln, danach den index
		System.out.printf("[%2d] %s%n", index(), messageResult);
	}

	/**
	 * Führt die Aktion aus, misst dabei die Anzahl an Millisekunden und gibt
	 * das Ergebnis aus.
	 * 
	 * @param action
	 *            die Aktion
	 * @see #measureAndPrintMessage(Supplier, Function)
	 */
	public static <T> void measureAndPrintMessage(Supplier<T> action) {
		AnalizationUtil.measureAndPrintMessage(action, t -> t);
	}

	/**
	 * Führt die Aktion aus, misst dabei die Anzahl an Millisekunden und gibt
	 * das Ergebnis aus.
	 * 
	 * @param action
	 *            die Aktion
	 * @param message
	 *            die Nachricht, die als Ergebnis ausgegeben werden soll
	 * @see #measureAndPrintMessage(Supplier)
	 */
	public static <T> void measureAndPrintMessage(Supplier<T> action, Function<? super T, Object> message) {
		long t1 = System.currentTimeMillis();
		T result = action.get();
		long t2 = System.currentTimeMillis();
		printMessage(() -> String.format("%dms: %s", t2 - t1, message.apply(result)));
	}

}
