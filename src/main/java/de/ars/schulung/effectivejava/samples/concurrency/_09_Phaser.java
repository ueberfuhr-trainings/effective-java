package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class _09_Phaser {

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

	/*
	 * Phaser (seit Java 7), wie CountDownLatch und CyclicBarrier,
	 * aber flexibler
	 * CountDownLatch
	 *  - feste Anzahl an Threads
	 *  - kein Resetten
	 *  - erlaubt Warten oder nur Herunterz�hlen
	 * CyclicBarrier
	 *  - Resetten m�glich
	 *  - feste Anzahl an Threads
	 * Phaser
	 *  - Anzahl an Threads muss beim Erzeugen des Objektes nicht bekannt sein
	 *  - Resetten m�glich
	 *  - blockierendes Warten oder nur Registrierung ohne Warten
	 *  - erlaubt mehrere Phasen
	 */

	public static void main(String[] args) throws InterruptedException {
		int cnt = 8;
		ExecutorService service = Executors.newFixedThreadPool(cnt);
		final Phaser phaser = new Phaser();
		final Random random = new Random();
		phaser.register(); // register self... for later join
		print(0, "Phase Count is " + phaser.getPhase());
		for (int i = 0; i < cnt; i++) {
			final int j = i;
			phaser.register(); // yet another thread to join
			service.submit(new Runnable() {

				@Override
				public void run() {
					print(j, "Bin am Start.");
					_sleep(1000);
					phaser.arriveAndAwaitAdvance();
					print(j, "Los!");
					_sleep(random.nextInt(6000));
					int numberWaiting = phaser.getArrivedParties();
					print(j, "Angekommen als " + (numberWaiting + 1) + ".!");
					phaser.arriveAndAwaitAdvance();
					print(j, "Gl�ckwunsch an alle!");
				}

			});
		}
		service.shutdown();
		phaser.arriveAndAwaitAdvance(); // join
		print(0, "Alle am Start. Phase Count is " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance(); // join
		print(0, "Finished! Phase Count is " + phaser.getPhase());
	}

}
