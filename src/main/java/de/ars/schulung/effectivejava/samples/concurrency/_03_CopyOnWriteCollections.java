package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class _03_CopyOnWriteCollections {

	public static void main(String[] args) throws InterruptedException {
		// CopyOnWriteCollections sind langsamer, weil intern ein Array erzeugt wird
		List<Character> list = new CopyOnWriteArrayList<>();
		list.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
		for (Character c : list) {
			list.remove(c);
		}
		System.out.println("Fertig!");
	}

}
