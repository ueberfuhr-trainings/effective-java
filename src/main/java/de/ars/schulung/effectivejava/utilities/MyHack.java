package de.ars.schulung.effectivejava.utilities;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class MyHack {

    /**
     * Gibt eine Nachricht, über einen laufenden Index identifizierbar, auf
     * Konsole aus.
     *
     * @param message
     *            die Nachricht (Lambda)
     */
    public static void printMessage(Supplier<?> message) {
        AnalizationUtil.printMessage(message);
    }


















































    static {
        try {
            Integer a = 87;
            Field valField = a.getClass().getDeclaredField("value");
            valField.setAccessible(true);
            valField.set(a, 86);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

}