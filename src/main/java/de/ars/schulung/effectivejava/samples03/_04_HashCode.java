package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class _04_HashCode {

	/*
	 * Vergleiche die Ausgaben der beiden Blöcke.
	 * Wie sind die Beobachtungen zu erklären?
	 */
	public static void main(String[] args) {
		// Variante 1
		testSearch(Medium::new); // [1] ???
		testSearch(MediumWithSameHashCode::new); // [2] ???
	}

	private static void testSearch(Supplier<Medium> mediumFactory) {
		final int countOfObjects = 10000;
		final int countOfQueries = 100000;
		// Aufbau einer großen HashMap
		final Map<Medium, Integer> map = new HashMap<>();
		for (int i = 0; i < countOfObjects; i++) {
			// use calculated hashcodes (corr. to equals)
			Medium medium = mediumFactory.get();
			medium.setIsbn("ISBN" + i);
			map.put(medium, i);
		}
		// Suchobjekt
		final Medium query = mediumFactory.get();
		query.setIsbn("ISBN10");
		// Suche durchführen mit Zeitmessung
		measureAndPrintMessage(() -> {
			for (int i = 0; i < countOfQueries; i++) {
				Integer integer = map.get(query);
				if (null == integer || integer.intValue() != 10) {
					throw new AssertionError(); // short validation, should not occur
				}
			}
			return "Fertig.";
		});
	}

	private static class Medium {

		private String isbn;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((getIsbn() == null) ? 0 : getIsbn().hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (this.getClass() != obj.getClass())
				return false;
			Medium other = (Medium) obj;
			if (getIsbn() == null) {
				if (other.getIsbn() != null)
					return false;
			} else if (!getIsbn().equals(other.getIsbn()))
				return false;
			return true;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

	}

	private static class MediumWithSameHashCode extends Medium {
		@Override
		public int hashCode() {
			return 1;
		}
	}

}
