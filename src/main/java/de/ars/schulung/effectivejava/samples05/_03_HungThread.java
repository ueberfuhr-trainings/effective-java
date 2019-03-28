package de.ars.schulung.effectivejava.samples05;

public class _03_HungThread extends Thread {

	boolean keepRunning = true;

	@Override
	public void run() {
		while (keepRunning);
		System.out.println("Thread terminated.");
	}

	/*
	 * Verhält sich die Anwendung korrekt?
	 * (Tip: Starte die JVM mit dem Kommandozeilenparameter "-server".)
	 */
	public static void main(String[] args) throws InterruptedException {
		_03_HungThread t = new _03_HungThread();
		t.start();
		Thread.sleep(1000);
		t.keepRunning = false;
		System.out.println("keepRunning set to " + t.keepRunning + ".");
	}
}