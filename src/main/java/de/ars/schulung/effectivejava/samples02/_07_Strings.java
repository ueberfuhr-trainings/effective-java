package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.*;

public class _07_Strings {

	/*
	 * Betrachte die Performance der beiden Varianten. Wie ist der Unterschied zu
	 * erklären?
	 */
	public static void main(String[] args) {
		final int count = 100000;
		measureAndPrintMessage(() -> {
			String text = "";
			for (int i = 0; i < count; i++) {
				text += 'a';
			}
			return text;
		}, String::length); // [1] ???
		measureAndPrintMessage(() -> {
			StringBuilder text = new StringBuilder();
			for (int i = 0; i < count; i++) {
				text.append('a');
			}
			return text.toString();
		}, String::length); // [2] ???
		measureAndPrintMessage(() -> {
			StringBuilder text = new StringBuilder(count);
			for (int i = 0; i < count; i++) {
				text.append('a');
			}
			return text.toString();
		}, String::length); // [3] ???
	}

}
