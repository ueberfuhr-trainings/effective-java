package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _05_BlockingQueue {

	private static void print(Integer index, Object text) {
		System.out.printf("[%s - %d] [%2d] %s%n", Thread.currentThread().getName(), System.currentTimeMillis(), index,
		        text);
	}

	private static void _sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// nur in eine Richtung
		final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(); // DelayedQueue
		new Thread() {

			@Override
			public void run() {
				try {
					print(1, "Starte...");
					_sleep(3000);
					messageQueue.put("Hi");
					print(1, "Fertig");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
		new Thread() {

			@Override
			public void run() {
				try {
					print(2, "Starte und warte...");
					print(2, messageQueue.take()); // poll is not blocking!
					print(2, "Fertig");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
		System.out.println("Fertig!");
	}

}
