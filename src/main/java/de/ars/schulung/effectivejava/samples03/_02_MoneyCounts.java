package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.math.BigDecimal;

public class _02_MoneyCounts {

	/*
	 * Ist u.g. Implementierung für die folgende Aufgabenstellung korrekt?
	 * 
	 * Du hast 10,95EUR. Ein Kaugummi kostet 0.73 EUR. Wieviel Kaugummis kannst Du
	 * kaufen, und wieviel Geld bleibt übrig?
	 */
	public static void main(String[] args) {
		BigDecimal money = BigDecimal.valueOf(1095, 2); // Achtung, kein .valueOf(10.95) verwenden, siehe interne
														// Implementierung
		final BigDecimal price = BigDecimal.valueOf(73, 2);
		int count = 0;
		while (money.compareTo(price) >= 0) { // solange Geld genug
			count++; // Zähler hoch
			money = money.subtract(price); // Preis abziehen
		}
		final int resultCount = count;
		final double resultMoney = money.doubleValue();
		printMessage(() -> String.format("%d Kaugummis, Restgeld %f EUR", resultCount, resultMoney));
	}
	/*
	 * Gleitkommazahlen sind ungenau, daher für genaue Berechnungen BigDecimal
	 * verwenden. - Achtung: Wir erzeugen wieder Objekte. - Alternativ im konkreten
	 * Fall mit Cents (long) rechnen.
	 */

}
