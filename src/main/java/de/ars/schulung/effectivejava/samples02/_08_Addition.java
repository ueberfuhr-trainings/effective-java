package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _08_Addition {

	/*
	 * Verhält sich die Anwendung wie erwartet?
	 */
	public static void main(String[] args) {
		int b = 2;
		int c = 87;
		printMessage(() -> b + c); // [1] 89
		Integer bb = b;
		Integer cc = c;
		printMessage(() -> bb + cc); // [2] 88
		/*
		 * Erklärung:
		 * ==========
		 * - ziemlich fieser Hack, der in der Klasse CounterUtil ganz unten zu finden ist
		 * - per Reflection wird dem Integer, dass unter dem Wert 87 gecacht ist, der interne Wert 86 zugewiesen
		 * - über die static Imports wird dieser Static Initializer ausgeführt
		 */
	}

}