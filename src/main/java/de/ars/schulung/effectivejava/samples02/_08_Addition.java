package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.MyHack.printMessage;

public class _08_Addition {

	/*
	 * Verhält sich die Anwendung wie erwartet?
	 *
	 * Hinweis:
	 *  - Dieses Beispiel lief mit älteren JVMs (bis Java 8) ohne zusätzliche Einstellungen.
	 *  - Seit Java 11 gibt es hier mein Starten eine Exception. Diese ist nicht gewünscht.
	 *  - Starte daher das Beispiel mit JVM-Option:
	 *    "--add-opens java.base/java.lang=effective.java.samples"
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