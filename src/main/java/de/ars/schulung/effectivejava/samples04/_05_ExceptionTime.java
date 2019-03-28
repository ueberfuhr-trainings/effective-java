package de.ars.schulung.effectivejava.samples04;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.printMessage;

import java.time.LocalDate;
import java.time.Period;

public class _05_ExceptionTime {

	/*
	 * Teste die Anwendung.
	 * Gibt es einen besseren Zeitpunkt für das Auftreten der Exception?
	 * Falls ja, wie muss Sie die Implementierung angepasst werden?
	 */
	public static void main(String[] args) {
		printMessage(() -> "Application started.");
		final Person person = new Person("Max", null);
		printMessage(() -> "Person created.");
		printMessage(() -> "Doing anything else...");
		// Imagine some other lines of code here...
		printMessage(() -> "Is it an adult?");
		final boolean adult = person.isAdult();
		printMessage(() -> adult ? "Yes, it's an adult." : "No, still a child.");
	}
	/*
	 * Erklärung:
	 * ==========
	 * Fail-fast wäre besser -> Prüfung im Konstruktor
	 */

	@SuppressWarnings("unused")
	private static class Person {

		private static final int ADULT_AGE = 18;

		private final String name;
		private final LocalDate birthDate;

		public Person(String name, LocalDate birthDate) {
			super();
			if(null == birthDate) {
				throw new IllegalArgumentException("birthdate must not be null!");
			}
			this.name = name;
			this.birthDate = birthDate;
		}

		public String getName() {
			return name;
		}

		public LocalDate getBirthDate() {
			return birthDate;
		}

		public boolean isAdult() {
			return Period.between(birthDate, LocalDate.now()).getYears() >= Person.ADULT_AGE;
		}
	}

}
