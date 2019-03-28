package de.ars.schulung.effectivejava.samples.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class _08_ForkJoinPool {

	private static void print(Integer index, Object text) {
		System.out.printf("[%s - %d] [%2d] %s%n", Thread.currentThread().getName(), System.currentTimeMillis(), index,
		        text);
	}

	private static class Fibonacci extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 1L;

		private final int n;

		public Fibonacci(int n) {
			this.n = n;
		}

		@Override
		public Integer compute() {
			print(n, "Berechne...");
			if (n <= 1)
				return n;
			Fibonacci f1 = new Fibonacci(n - 1);
			f1.fork();
			Fibonacci f2 = new Fibonacci(n - 2);
			f2.fork();
			return f2.join() + f1.join();
		}
	}

	/*
	 * ForkJoinPool seit Java7
	 * Hardware-Parallelit�t im Vgl. zu ExecutorService
	 * 
	 * Fork/Join addresses the need for divide-and-conquer, or recursive task-processing in Java
	 * Fork/Join's logic is very simple: 
	 * (1) separate (fork) each large task into smaller tasks; 
	 * (2) process each task in a separate thread (separating those into even smaller tasks if necessary);
	 * (3) join the results.  
	 */

	/*
	* ForkJoinTasks are {@code Serializable}, which enables them to be
	* used in extensions such as remote execution frameworks. It is
	* sensible to serialize tasks only before or after, but not during,
	* execution. Serialization is not relied on during execution itself.
	 */

	public static void main(String[] args) throws InterruptedException {
		// in beide Richtungen/synchron
		final ForkJoinPool pool = new ForkJoinPool(); // Anzahl Threads = Anzahl Prozessoren

		// Ermittle d
		int result = pool.invoke(new Fibonacci(10));
		System.out.println("Fertig! Ergebnis = " + result);
	}

}
