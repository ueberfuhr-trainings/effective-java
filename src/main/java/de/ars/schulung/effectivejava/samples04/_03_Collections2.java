package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.Arrays;

public class _03_Collections2 {

	/*
	 * Welche Ausgaben werden erzeugt?
	 */
	public static void main(String[] args) {
		printMessage(() -> Arrays.asList(new Object[] { 1, 2, 3 })); // [1] [1, 2, 3]
		printMessage(() -> Arrays.asList(new int[] { 1, 2, 3 })); // [2] [[I@41629346]
		/*
		 * Erklärung:
		 * ==========
		 * Dieses Problem besteht bei der Kombination Generics/Varargs.
		 * - Generics sind Referenztypen.
		 * - Arrays sind Referenztypen.
		 * - Der Compiler erkennt das int-Array als einzelnes Objekt. (kein Autoboxing)
		 */
	}

}
