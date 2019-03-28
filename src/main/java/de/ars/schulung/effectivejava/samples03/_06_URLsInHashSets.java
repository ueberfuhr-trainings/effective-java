package de.ars.schulung.effectivejava.samples03;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class _06_URLsInHashSets {

	/*
	 * AUFGABE:
	 * ========
	 * Vergleiche die Performance der beiden folgenden Varianten.
	 * Wie lassen sich die Beobachtungen erklären?
	 */
	public static void main(String[] args) throws MalformedURLException {
		// Setup: Aufbau einer Liste von URLs
		final URL[] urls = new URL[100000]; // ggf. anpassen
		final int count = 10;
		for (int i = 0; i < urls.length; i++) {
			String url = String.format("http://www.server%s.ars.de/api", i % count);
			urls[i] = new URL(url);
		}
		Collection<URL> urlCol = Arrays.asList(urls);

		// Gegeben sei jetzt eine Liste von URLs mit Dopplungen, die per Set eliminiert werden sollen

		// Variante 1
		testWith(urlCol, HashSet::new); // [1] inperformant
		// Variante 2
		testWith(urlCol, c -> c.stream().map(URL::toExternalForm).collect(Collectors.toSet())); // [2] performant

	}
	/*
	 * Die Klasse URL implementiert hashCode() und equals() nach der Semantik, dass der Host
	 * inhaltlich gleich ist, wenn dahinter die gleiche IP-Adresse steckt. Es wird also ein
	 * DNS-Lookup gemacht, der natürlich inperformanter ist.
	 */

	private static void testWith(Collection<URL> urlCol, Function<Collection<URL>, Collection<?>> f) {
		measureAndPrintMessage(() -> {
			return f.apply(urlCol);
		}, col -> col.size() + " Elemente");
	}
}
