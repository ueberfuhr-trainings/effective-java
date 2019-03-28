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
		}); // [2] performant
		// Variante 2
		Function<Long, Long> f = sum -> {
			for (int i = 0; i < Integer.MAX_VALUE; sum += i++)
				;
			return sum;
		};
		measureAndPrintMessage(() -> {
			long sum = 0L;
			return f.apply(sum);
		}); // [3] inperformant
		/*
		 * Erklärung:
		 * ==========
		 * Beim zweiten Beispiel nutzen wir eine Lambda in Verbindung mit Generics, für die nur Referenztypen eingesetzt
		 * werden können, und erzeugen so aus dem
		 * primitiven long ein Long-Objekt. Die Addition von Long-Objekten führt implizit zu Auto-Unboxing und
		 * Auto-Boxing. Letzteres erzeugt Objekte, was dann zu Performanzverlust führt.
		 */
	}

}
