package de.ars.schulung.effectivejava.samples06;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class _01_Lambda_vs_Anonymous_Class2 {

	// Hilfstyp mit Referenz auf Summe für Fall [2]
	private static class Reference<T> {
		private T value;

		public Reference(T value) {
			this.value = value;
		}

		public T get() {
			return value;
		}

		public void set(T value) {
			this.value = value;
		}

	}

	/*
	 * AUFGABE: ======== Vergleiche die Performanz der folgenden Blöcke. Wie lassen
	 * sich die Beobachtungen erklären?
	 */
	public static void main(String[] args) {
		final int size = 10000000;
		final List<Integer> numbers = new LinkedList<>();
		for (int i = 100; i < size+100; i++) { // +100 wegen Integer-Manipulation aus Samples02/08
			numbers.add(i);
		}
		// Ausführung 1: [1] ???
		measureAndPrintMessage(() -> {
			int sum = 0;
			for (Integer nr : numbers) {
				sum += nr;
			}
			return sum;
		}, sum -> Integer.toString(sum));
		// Ausführung 2: [2] ???
		measureAndPrintMessage(() -> {
			return numbers.stream().collect( //
					() -> new Reference<Integer>(0), // Startwert für Summe = 0
					(sum, x) -> sum.set(sum.get() + x), // für jede Zahl addiere zur Summe
					(sum1, sum2) -> sum1.set(sum1.get() + sum2.get()) // (parallele Verarbeitung: 2 Summen kombinieren)
			);
		}, sum -> Integer.toString(sum.get()));
		// Ausführung 3: [3] ???
		measureAndPrintMessage(() -> {
			return numbers.stream().collect(Collectors.summingInt(Integer::intValue));
		}, sum -> Integer.toString(sum));
	}

}
