package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _06_Trivial {

	// Welche Ausgaben werden erzeugt?
	public static void main(String args[]) {
		printMessage(() -> String.format("%s%s%d", "H", "a", 124 + 56)); // [1] ???
		printMessage(() -> "H" + "a" + 124 + 56); // [2] ???
		printMessage(() -> "H" + "a" + (124 + 56)); // [3] ???
		printMessage(() -> 124 + 56 + "H" + "a"); // [4] ???
		printMessage(() -> 'H' + 'a'); // [5] ???
		printMessage(() -> (char) ('H' + 'a')); // [6] ???
	}
}