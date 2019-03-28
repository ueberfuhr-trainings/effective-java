package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class _06_ExceptionDriven {

	/*
	 * Warum gibt es bei der Performance der beiden Varianten so große Unterschiede?
	 */
	public static void main(String[] args) {
		final int count = 1000000;
		final Collection<String> messages = Arrays.asList("x", "y");
		measureAndPrintMessage(() -> {
			for (int i = 0; i < count; i++) {
				Iterator<String> it = messages.iterator();
				try {
					while (true) {
						it.next();
					}
				} catch (NoSuchElementException e) {
					// fertig
				}
			}
			return "Fertig";
		}); // [1] ???
		measureAndPrintMessage(() -> {
			for (int i = 0; i < count; i++) {
				Iterator<String> it = messages.iterator();
				while (it.hasNext()) {
					it.next();
				}
			}
			return "Fertig";
		}); // [2] ???
	}

}
