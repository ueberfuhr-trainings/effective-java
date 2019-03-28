package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _06_Trivial {

	// Welche Ausgaben werden erzeugt?
	public static void main(String args[]) {
		printMessage(() -> String.format("%s%s%d", "H", "a", 124 + 56)); // [1] Ha180
		printMessage(() -> "H" + "a" + 124 + 56); // [2] Ha12456
		printMessage(() -> "H" + "a" + (124 + 56)); // [3] Ha180
		printMessage(() -> 124 + 56 + "H" + "a"); // [4] 180Ha
		printMessage(() -> 'H' + 'a'); // [5] 169
		printMessage(() -> (char) ('H' + 'a')); // [6] ©
		/*
		 * Erklärung:
		 * ==========
		 * - Operator + ist überladen (Addition vs. String-Konkatenation)
		 * - Operator + ist binär und linksgerichtet. Er addiert die Zahlen, sobald von links ein String dabei ist, konkateniert er Strings
		 * - chars werden vor dem + in int konvertiert (Unicode-Wert), somit wird addiert
		 */
	}
}