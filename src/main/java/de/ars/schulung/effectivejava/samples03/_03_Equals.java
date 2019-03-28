package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

@SuppressWarnings("unused")
public class _03_Equals {

	/*
	 * Ist die Implementierung von equals() in Medium korrekt?
	 * (2 Medien/Buecher sind inhaltlich gleich, wenn deren ISBN übereinstimmt.)
	 */

	public static void main(String[] args) {
		Medium m1 = new Medium();
		m1.setIsbn("12345");
		m1.setTitle("Ein Buch.");
		Book b1 = new Book();
		b1.setIsbn("12345");
		b1.setPages(100);
		b1.setTitle("Ein Buch.");
		Book b2 = new Book();
		b2.setIsbn("12345");
		b2.setPages(100);
		b2.setTitle("Ein Buch.");
		// Reflexiv
		printMessage(() -> m1.equals(m1)); // [1] ???
		printMessage(() -> b1.equals(b1)); // [2] ???
		// Symmetrisch
		printMessage(() -> m1.equals(b1)); // [3] ???
		printMessage(() -> b1.equals(m1)); // [4] ???
		printMessage(() -> b1.equals(b2)); // [5] ???
		printMessage(() -> b2.equals(b1)); // [6] ???
	}

	private static class Medium {
		private String isbn;
		private String title;

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (Medium.class != obj.getClass())
				return false;
			Medium other = (Medium) obj;
			if (isbn == null) {
				if (other.isbn != null)
					return false;
			} else if (!isbn.equals(other.isbn))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}

	}

	private static class Book extends Medium {
		private int pages;

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

	}

}
