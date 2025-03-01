package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class _02_Collections {

	/*
	 * Welche Ausgaben werden erzeugt?
	 */
	public static void main(String[] args) {
		printMessage(() -> {
			List<Integer> list = new ArrayList<>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.remove(2);
			return list;
		}); // [1] ???
		printMessage(() -> {
			Collection<Integer> list = new ArrayList<>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.remove(2);
			return list;
		}); // [2] ???
	}

}
