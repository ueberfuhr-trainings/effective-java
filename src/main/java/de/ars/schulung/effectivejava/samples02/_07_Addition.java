package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _07_Addition {

	/*
	 * Verhält sich die Anwendung wie erwartet?
	 */
	public static void main(String[] args) {
		int b = 2;
		int c = 87;
		printMessage(() -> b + c); // [1] ???
		Integer bb = b;
		Integer cc = c;
		printMessage(() -> bb + cc); // [2] ???
	}

}