package de.ars.schulung.effectivejava.samples05;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _02_ThreadLocal {

	/*
	 * AUFGABE:
	 * ========
	 * Wie verhält sich die ThreadLocal-Instanz bei Verwendung eines Thread-Pools?
	 * Testen Sie auch mit der Einstellung threadPoolThreads == countOfExecutions
	 * (Optional: Warum sind die Ausgaben nicht zwingend sortiert?)
	 */

	public static void main(String[] args) throws InterruptedException {
		final int countOfExecutions = 10; // ggf. anpassen
		final int threadPoolThreads = 3; // ggf. anpassen
		// Dieses Objekt steht im Fokus
		final ThreadLocal<String> messageTl = new ThreadLocal<>();
		final Runnable algorithm = () -> {
			final String tn = Thread.currentThread().getName(); // Name des Threads zum Nachvollziehen
			printMessage(() -> String.format("[%s], Erwarte leere Message! -> %s", tn, messageTl.get()));
			messageTl.set(String.format("Kuckuck sagt %s!", tn));
			printMessage(() -> String.format("[%s], Erwarte Message gesetzt! -> %s", tn, messageTl.get()));
		};
		// Variante 1
		printMessage(() -> {
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
		}); // [1] wie erwartet
		// Variante 2
		printMessage(() -> {
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
		}); // [2] ThreadLocal übernimmt Werte aus vorheriger Thread-Ausführung
	}

}
