package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class _06_DelayQueue {

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

	private static class DelayedMessage implements Delayed {

		private final String message;
		private final long timeToReceive;

		public DelayedMessage(String message, long time, TimeUnit timeUnit) {
			super();
			this.message = message;
			this.timeToReceive = TimeUnit.MILLISECONDS.convert(time, timeUnit) + System.currentTimeMillis();
		}

		@Override
		public int compareTo(Delayed o) {
			return Long.valueOf(getDelay(TimeUnit.MILLISECONDS)).compareTo(o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(timeToReceive - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		public String getMessage() {
			return message;
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// nur in eine Richtung
		final BlockingQueue<DelayedMessage> messageQueue = new DelayQueue<>(); // DelayedQueue
		new Thread() {

			@Override
			public void run() {
				try {
					print(1, "Starte...");
					_sleep(3000);
					messageQueue.put(new DelayedMessage("Hi", 5, TimeUnit.SECONDS));
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
					print(2, messageQueue.take().getMessage()); // poll is not blocking!
					print(2, "Fertig");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
		System.out.println("Fertig!");
	}

}
