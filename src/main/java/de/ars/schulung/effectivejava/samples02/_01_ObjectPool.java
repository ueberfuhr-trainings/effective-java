package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _01_ObjectPool {

	/*
	 * AUFGABE:
	 * ========
	 * Welche Ausgaben erzeugen folgende Anweisungen?
	 */

	public static void main(String[] args) {

		String s1 = "text";
		String s2 = "text";

		printMessage(() -> s1 == s2); // [1] true (JLS §3.10.05)
		printMessage(() -> new String(s1) == s2); // [2] false
		printMessage(() -> new String(s1).intern() == s2); // [3] true

		String s3 = "te" + "xt";
		printMessage(() -> s1 == s3); // [4] true (JLS §4.3.3)

		String te = "te";
		final String xt = "xt";

		String s4 = te + "xt";
		printMessage(() -> s1 == s4); // [5] false
		printMessage(() -> s1 == s4.intern()); // [6] true

		String s5 = "te" + xt;
		printMessage(() -> s1 == s5); // [7] true, da xt final

		Integer i1 = 4;
		Integer i2 = 4;
		Integer i3 = new Integer(4);

		printMessage(() -> i1 == i2); // [8] true
		printMessage(() -> i1 == i3); // [9] false

		Integer i4 = -129;
		Integer i5 = -129;

		printMessage(() -> i4 == i5); // [10] false

	}

}
