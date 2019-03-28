package de.ars.schulung.effectivejava.samples02;

public class _03_CompilerError {

	/*
	 * Warum gibt es hier einen Compilerfehler?
	 */
	public static void main(String[] args) {
		char c = 0x000a;
		System.out.println("c=" + c); // JLS 3.2
		// \u000a ist ein Zeilenumbruch;

	}
}
