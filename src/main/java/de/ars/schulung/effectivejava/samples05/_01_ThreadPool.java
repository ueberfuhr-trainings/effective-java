package de.ars.schulung.effectivejava.samples05;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _01_ThreadPool {

	/*
	 * AUFGABE:
	 * ========
	 * Vergleiche die Performance der beiden folgenden Varianten.
	 * Wie lassen sich die Beobachtungen erklären?
	 */
	public static void main(String[] args) throws InterruptedException {
		final int countOfExecutions = 10000; // ggf. anpassen
		final int threadPoolThreads = 3; // ggf. anpassen
		final Runnable algorithm = () -> {
			; // nothing to do here, just a short action
		};
		/*
		 * Anforderung:
		 *  - Führe den o.g. Algorithmus 10000 ( countOfExecutions ) mal aus.
		 *  - Erledige diese Aufgabe so performant wie möglich.
		 */
		// Variante 1
		measureAndPrintMessage(() -> {
			final Collection<Thread> threads = new LinkedList<>();
			for (int i = 0; i < countOfExecutions; i++) {
				Thread t = new Thread(algorithm); // new Thread
				threads.add(t);
				t.start();
			}
			// wait for all
			try {
				for (Thread t : threads) {
					t.join(120000l);
				}
				return "Fertig.";
			} catch (InterruptedException e) {
				return String.format("%s%n%s", e.getMessage(), Arrays.toString(e.getStackTrace()));
			}
		}); // [1] inperformant
		// Variante 2
		measureAndPrintMessage(() -> {
			final ExecutorService pool = Executors.newFixedThreadPool(threadPoolThreads);
			String message;
			try {
				for (int i = 0; i < countOfExecutions; i++) {
					pool.submit(algorithm); // Threads re-used
				}
			} finally {
				pool.shutdown();
				try {
					pool.awaitTermination(2, TimeUnit.MINUTES);
					message = "Fertig.";
				} catch (InterruptedException e) {
					message = String.format("%s%n%s", e.getMessage(), Arrays.toString(e.getStackTrace()));
				}
			}
			return message;
		}); // [2] performant
		/*
		 * Erklärung:
		 * ==========
		 * Thread Pools sind performanter, denn eine große Menge an Threads sorgt für zu viel Verwaltungsaufwand
		 * (v.a. Thread erzeugen, evtl. auch Wechsel zwischen Threads bei Quasi-Parallelität).
		 */
	}

}
