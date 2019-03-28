package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

public class _02_MoneyCounts {

	/*
	 * Ist u.g. Implementierung für die folgende Aufgabenstellung korrekt?
	 * 
	 * Du hast 10,95EUR. Ein Kaugummi kostet 0.73 EUR. Wieviel Kaugummis
	 * kannst Du kaufen, und wieviel Geld bleibt übrig?
	 */
	public static void main(String[] args) {
		double money = 10.95;
		final double price = .73;
		int count = 0;
		while (money >= price) { // solange Geld genug
			count++; // Zähler hoch
			money -= price; // Preis abziehen
		}
		final int resultCount = count;
		final double resultMoney = money;
		printMessage(() -> String.format("%d Kaugummis, Restgeld %f EUR", resultCount, resultMoney));
	}

}
