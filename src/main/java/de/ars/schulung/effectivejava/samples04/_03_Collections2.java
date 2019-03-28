package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.Arrays;

public class _03_Collections2 {

	/*
	 * Welche Ausgaben werden erzeugt?
	 */
	public static void main(String[] args) {
		printMessage(() -> Arrays.asList(new Object[] { 1, 2, 3 })); // [1] ???
		printMessage(() -> Arrays.asList(new int[] { 1, 2, 3 })); // [2] ???
	}

}
