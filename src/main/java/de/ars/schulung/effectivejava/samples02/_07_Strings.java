package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.*;

public class _07_Strings {

	/*
	 * Betrachte die Performance der beiden Varianten. Wie ist der Unterschied zu
	 * erklären?
	 */
	public static void main(String[] args) {
		final int count = 100000;
		measureAndPrintMessage(() -> {
			String text = "";
			for (int i = 0; i < count; i++) {
				text += 'a';
			}
			return text;
		}, String::length); // [1] sehr inperformant
		measureAndPrintMessage(() -> {
			StringBuilder text = new StringBuilder();
			for (int i = 0; i < count; i++) {
				text.append('a');
			}
			return text.toString();
		}, String::length); // [2] schon gut
		measureAndPrintMessage(() -> {
			StringBuilder text = new StringBuilder(count);
			for (int i = 0; i < count; i++) {
				text.append('a');
			}
			return text.toString();
		}, String::length); // [3] noch besser, v.a. wenn count nocht größer!
		/*
		 * Erklärung:
		 * ==========
		 * - Variante1 erzeugt viele String-Objekte
		 * - Variante2 vermeidet das Erzeugen von Objekten
		 * - Variante3 wie Variante 2, aber belegt das interne byte-Array im StringBuilder vor, sodass keine Verlängerung notwendig ist
		 */
	}

}
