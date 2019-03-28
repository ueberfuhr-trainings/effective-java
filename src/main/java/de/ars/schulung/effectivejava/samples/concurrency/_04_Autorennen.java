package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _04_Autorennen {

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
		int cnt = 8;
		ExecutorService service = Executors.newFixedThreadPool(cnt);
		final CountDownLatch start = new CountDownLatch(cnt);
		final CyclicBarrier finish = new CyclicBarrier(cnt);
		final Random random = new Random();
		for (int i = 0; i < cnt; i++) {
			final int j = i;
			service.submit(new Runnable() {

				@Override
				public void run() {
					print(j, "Bin am Start.");
					_sleep(2000);
					start.countDown();
					print(j, "Los!");
					_sleep(random.nextInt(12000));
					int numberWaiting = finish.getNumberWaiting();
					print(j, "Angekommen als " + (numberWaiting + 1) + ".!");
					try {
						finish.await();
						print(j, "Gl�ckwunsch an alle!");
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}

			});
		}
		service.shutdown();
		service.awaitTermination(5, TimeUnit.MINUTES);
		System.out.println("Fertig!");
	}

}
