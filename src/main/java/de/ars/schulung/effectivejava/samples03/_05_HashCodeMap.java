package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.HashMap;
import java.util.Map;

public class _05_HashCodeMap {

	/*
	 * Analysiere das Verhalten der Anwendung.
	 * Welche Schlussfolgerungen ziehst Du?
	 */

	public static void main(String[] args) {
		final Map<Medium, Integer> map = new HashMap<>();
		// put into map
		Medium medium = new Medium();
		medium.setIsbn("12345");
		map.put(medium, 12345);
		// read from map
		printMessage(() -> "Value is " + map.get(medium)); // [1] Value is 12345
		medium.setIsbn("54321");
		printMessage(() -> "Value is " + map.get(medium)); // [2] Value is null
	}
	/*
	 * Erklärung:
	 * ==========
	 * Nach der ersten Ausgabe wird der Zustand des Mediums geändert.
	 * Damit ändert sich auch der hashCode(), und das Objekt wird in der Map nicht mehr gefunden.
	 *
	 * Fazit:
	 * Objekte, die als Key dienen, nicht ändern. (evtl. immutable designen)
	 */

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

}
