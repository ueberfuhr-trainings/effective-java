package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

@SuppressWarnings("static-access")
public class _05_Bark {

	private static class Dog {
		public static void bark() {
			printMessage(() -> "woof");
		}
	}

	private static class Basenji extends Dog {
		public static void bark() {
			printMessage(() -> "wow");
		}
	}

	public static void main(String args[]) {

		// Welche Ausgaben werden erzeugt?

		Dog woofer = new Dog();
		Dog nipper = new Basenji();
		woofer.bark();
		nipper.bark();
		
		/*
		 * Erklärung:
		 * ==========
		 * Die Methoden sind statisch gebunden.
		 * Daher zählt nicht der Laufzeittyp, sondern der Typ, der zur Compile Time ermittelt wird.
		 * 
		 * Lösungsansätze:
		 * ===============
		 * - nipper vom Typ Basenji statt Dog
		 * - Schreibweise Klasse.methode() verwenden
		 * - Methoden dynamisch binden (static entfernen)
		 */
	}
}