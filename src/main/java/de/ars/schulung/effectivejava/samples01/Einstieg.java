package de.ars.schulung.effectivejava.samples01;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printHex;

public class Einstieg {

	/*
	 * Folgende Addition soll implementiert werden:
	 *     CAFE BABE
	 * + 1 0000 0000
	 * =============
	 *   1 CAFE BABE
	 * 
	 * Gibt das Programm den erwarteten Wert zurück?
	 * Wenn nein, warum nicht?
	 */
	public static void main(String[] args) {
		long l1 = 0x0_cafe_babe;
		long l2 = 0x1_0000_0000L;
		printHex(() -> l1 + l2);
	}

}
