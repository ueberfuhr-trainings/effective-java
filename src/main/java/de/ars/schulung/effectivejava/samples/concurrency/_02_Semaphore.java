package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class _02_Semaphore {

	private static void print(Integer index, Object text) {
		System.out.printf("[%s - %d] [%2d] %s%n", Thread.currentThread().getName(), System.currentTimeMillis(), index,
		        text);
	}

	private static void _sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Semaphore semaphore = new Semaphore(3);

	private static class Worker extends Thread {

		private final int counter;

		public Worker(int counter) {
			super();
			this.counter = counter;
		}

		@Override
		public void run() {
			print(counter, "Running thread.");
			try {
				semaphore.acquire();
				try {
					print(counter, "Running synchronized block.");
					_sleep(Math.round(10000 * Math.random()));
				} finally {
					print(counter, "Unlocking.");
					semaphore.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		int cnt = 8;
		ExecutorService service = Executors.newFixedThreadPool(cnt);
		for (int i = 0; i < cnt; i++) {
			service.submit(new Worker(i));
		}
		service.shutdown();
		service.awaitTermination(5, TimeUnit.MINUTES);
		System.out.println("Fertig!");
	}

}
