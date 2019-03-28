package de.ars.schulung.effectivejava.samples02;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.time.LocalDate;

public class _04_StaticInitialization {

	private static class Elvis {

		public static final Elvis INSTANCE = new Elvis();
		private static final int CURRENT_YEAR = LocalDate.now().getYear();

		private final int beltSize;

		private Elvis() {
			beltSize = CURRENT_YEAR - 1930;
		}

		public int beltSize() {
			return beltSize;
		}
	}

	/*
	 * Welche Ausgabe wird hier erzeugt?
	 */
	public static void main(String[] args) {
		printMessage(() -> Elvis.CURRENT_YEAR); // [1] ???
		printMessage(() -> String.format("Elvis wears a size %d belt.", Elvis.INSTANCE.beltSize())); // [2] ???
	}
}