package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _01_Lock {

	// Vorteil von Locks -> Verwendung �ber mehrere Methoden hinweg (nicht empfohlen)

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

	private static Lock lock = new ReentrantLock(); // ReentrantReadWriteLock kann unterteilen in Read oder Write

	private static class Worker extends Thread {

		private final int counter;

		public Worker(int counter) {
			super();
			this.counter = counter;
		}

		@Override
		public void run() {
			print(counter, "Running thread.");
			lock.lock();
			try {
				print(counter, "Running synchronized block.");
				_sleep(3000);
			} finally {
				print(counter, "Unlocking.");
				lock.unlock();
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
