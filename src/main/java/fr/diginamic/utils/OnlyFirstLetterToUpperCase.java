package fr.diginamic.utils;

public class OnlyFirstLetterToUpperCase {
    public static String onlyFirstLetterToUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String lowercaseStr = str.toLowerCase();
        char firstChar = Character.toUpperCase(lowercaseStr.charAt(0));


        String finalString = firstChar + lowercaseStr.substring(1);
        return finalString;
    }
}
