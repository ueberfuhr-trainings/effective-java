package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.function.Supplier;

public class _02_ObjectCreation {

	/*
	 * AUFGABE:
	 * ========
	 * Vergleiche die Performanz der beiden folgenden Blöcke.
	 * Wie lassen sich die Beobachtungen erklären?
	 */
	public static void main(String[] args) {
		final int count = 1000000; // ggf. anpassen
		final int objectSize = 1000; // ggf. anpassen
		// Ausführung 1: [1] ???
		execute(() -> {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				Konto k1 = new Konto(objectSize);
				k1.setNr(Integer.toString(i));
				sb.append(k1.toString()).append('\n');
			}
			return sb.toString();
		});
		// Ausführung 2: [2] ???
		execute(() -> {
			StringBuilder sb = new StringBuilder();
			Konto k1 = new Konto(objectSize);
			for (int i = 0; i < count; i++) {
				k1.setNr(Integer.toString(i));
				sb.append(k1.toString()).append('\n');
			}
			return sb.toString();
		});
	}

	@SuppressWarnings("unused")
	private static class Konto {
		private String nr;
		private final int[] internalArray;

		public Konto(int objectSize) {
			internalArray = new int[objectSize];
		}

		public String getNr() {
			return nr;
		}

		public void setNr(String nr) {
			this.nr = nr;
		}

		@Override
		public String toString() {
			return "Kontonummer: " + nr;
		}

	}

	private static void execute(Supplier<String> action) {
		measureAndPrintMessage(action, (text) -> text.length() + " Zeichen");
	}

}