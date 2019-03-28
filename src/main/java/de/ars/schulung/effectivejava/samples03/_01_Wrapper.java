package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;
import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.function.Function;

public class _01_Wrapper {

	/*
	 * Teste die Anwendung.
	 * Wie ist das beobachtete Verhalten zu erklären?
	 * Zusatz: Was passiert, wenn "i<=Integer.MAX_VALUE" als Schleifenbedingung verwendet wird?
	 */

	public static void main(String[] args) {
		printMessage(() -> "Application started.");
		// Variante 1
		measureAndPrintMessage(() -> {
			long sum = 0L;
			for (int i = 0; i < Integer.MAX_VALUE; sum += i++)
				;
			return sum;
		}); // [2] ???
		// Variante 2
		Function<Long, Long> f = sum -> {
			for (int i = 0; i < Integer.MAX_VALUE; sum += i++)
				;
			return sum;
		};
		measureAndPrintMessage(() -> {
			long sum = 0L;
			return f.apply(sum);
		}); // [3] ???
	}

}
