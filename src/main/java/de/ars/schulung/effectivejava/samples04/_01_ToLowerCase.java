package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _01_ToLowerCase {

	/*
	 * Hat u.g. Ausgabe stets verlässlich denselben Wert?
	 */
	public static void main(String[] args) {
		String s = "\u00cc";
		final int i = s.length();
		final int j = s.toLowerCase().length();
		printMessage(() -> i == j);
	}
	/*
	 * Nein, z.B. bei Start mit Parameter -Duser.language=lt.
	 * Typisches I18n-Problem. Vorsicht also, wenn Logik sich
	 * darauf verlässt.
	 * Stattdessen lieber String#toLowerCase(Locale) verwenden.
	 */

}
