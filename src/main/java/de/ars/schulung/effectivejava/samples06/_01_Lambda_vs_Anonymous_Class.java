package de.ars.schulung.effectivejava.samples06;

import static de.ars.schulung.effectivejava.utilities.AnalizationUtil.measureAndPrintMessage;

import java.util.function.Consumer;

public class _01_Lambda_vs_Anonymous_Class {

	@FunctionalInterface
	private static interface StringAppender {
		void append(StringBuilder sb, String text);
	}
	
	/*
	 * AUFGABE: ======== Vergleiche die Performanz der folgenden Blöcke. Wie lassen
	 * sich die Beobachtungen erklären?
	 */
	public static void main(String[] args) {
		final int size = 10000000;
		final String[] strings = new String[size];
		for(int i = 0; i<size; i++) {
			strings[i] = Integer.toString(i);
		};
		// Ausführung 1: [1] ???
		measureAndPrintMessage(() -> {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				sb.append(strings[i]);
			}
			return sb;
		}, sb -> sb.length());
		// Ausführung 2: [2] ???
		measureAndPrintMessage(() -> {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				Consumer<String> consumer = new Consumer<String>() {

					@Override
					public void accept(String t) {
						sb.append(t);
					}
				};
				consumer.accept(strings[i]);
			}
			return sb;
		}, sb -> sb.length());
		// Ausführung 3: [3] ???
		measureAndPrintMessage(() -> {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				Consumer<String> consumer = sb::append;
				consumer.accept(strings[i]);
			}
			return sb;
		}, sb -> sb.length());
		// Ausführung 4: [4] ???
		measureAndPrintMessage(() -> {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				StringAppender appender = (builder, s) -> builder.append(s);
				appender.append(sb, strings[i]);
			}
			return sb;
		}, sb -> sb.length());
		// Ausführung 5: [5] ???
		measureAndPrintMessage(() -> {
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				StringAppender appender = (builder, s) -> sb.append(s);
				appender.append(sb, strings[i]);
			}
			return sb;
		}, sb -> sb.length());
	}

}
