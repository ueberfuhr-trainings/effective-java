package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

public class _04_ExceptionsInLambdas {

	/*
	 * Finde eine Lösung für folgende Compiler-Problematik:
	 */
	public static void main(String[] args) {
		try {
			executeAction();
		} catch (IOException e) {
			printMessage(() -> "Exception ist aufgetreten. Hier wollen wir sie behandeln.");
		}
	}

	/*
	 * - nur diese beiden Methoden dürfen verändert werden
	 * - es muss printMessage(...) mit λ aufgerufen werden
	 */
	private static void executeAction() throws IOException {
		Supplier<String> λ = () -> readFile("demo.txt");
		printMessage(λ);
	}

	private static String readFile(String name) throws IOException {
		try (InputStream in = new FileInputStream(name)) {
			// read file content here...
			return "this is a sample content";
		}
	}

}
