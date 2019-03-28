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
		
		/*
		 * Erklärung:
		 *  - 0xCAFE BABE ist 32-bit int mit führender 1 (C = 1101, negative Zahl)
		 *  - bei Zuweisung an 64-bit long Variable werden führende 1er vorn aufgefüllt
		 *  - Damit ist die Addition dann
		 *  
		 *    FFFF FFFF CAFE BABE
		 *  + 0000 0001 0000 0000
		 *    ===================
		 *    0000 0000 CAFE BABE
		 * 
		 * Lösung:
		 *  - bei Zuweisung direkt einen long-Wert angeben (L)
		 */
		
		long l1 = 0x0_cafe_babeL;
		long l2 = 0x1_0000_0000L;
		printHex(() -> l1 + l2);
	}

}
