package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.Exchanger;

public class _07_Exchanger {

	private static void print(Integer index, Object text) {
		System.out.printf("[%s - %d] [%2d] %s%n", Thread.currentThread().getName(), System.currentTimeMillis(), index,
		        text);
	}

	public static void main(String[] args) throws InterruptedException {
		// in beide Richtungen/synchron
		final Exchanger<String> exchanger = new Exchanger<>();
		new Thread() {

			@Override
			public void run() {
				try {
					print(1, "Starte...");
					String message = exchanger.exchange("Hi");
					print(1, message);
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
					String message = exchanger.exchange("Hall�chen");
					print(2, message);
					print(2, "Fertig");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
		System.out.println("Fertig!");
	}

}
