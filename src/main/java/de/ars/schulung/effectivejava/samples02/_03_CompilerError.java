package de.ars.schulung.effectivejava.samples02;

public class _03_CompilerError {

	/*
	 * Warum gibt es hier einen Compilerfehler?
	 */
/* KOMMENTARZEICHEN BITTE ENTFERNEN
	public static void main(String[] args) {
		char c = 0x000a;
		System.out.println("c=" + c); // JLS 3.2
		// \u000a ist ein Zeilenumbruch;

	}
   KOMMENTARZEICHEN BITTE ENTFERNEN */

	/*
	 * Erklärung:
	 * ==========
	 * Der Java-Compiler wandelt VOR dem Kompilieren Unicode-Sequenzen um.
	 * Damit endet mit dem "\u000a" der einzeilige Kommentar, der Compiler versucht,
	 * den Text dahinter zu kompilieren.
	 * 
	 * Heikel wird es bei kompilierfähigen Kommentaren, z.B.
	 * // \u000a System.exit(0);
	 * 
	 * Lösungsansätze:
	 * ===============
	 *  - mehrzeiliger Kommentar
	 *  - \\u000a
	 * 
	 */
}
