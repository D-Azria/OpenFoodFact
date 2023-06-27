package fr.diginamic.utils;

public class RemovePrefix {
    public static String removePrefix(String string, String prefix) {
        String modifiedString = null;

            int index = string.indexOf(prefix);
            if (index != -1 && index + prefix.length() < string.length()) {
                modifiedString = string.substring(index + prefix.length()).trim();
            }
        return modifiedString;
    }
}
