package fr.diginamic.processing.parse.fineParse;

public class RemoveAfterAsterisk {
    public static String removeAsterisk(String input) {
        StringBuilder modifiedString = new StringBuilder();
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.contains("*")) {
                int asteriskIndex = word.indexOf("*");
                if (asteriskIndex + 1 < word.length() && word.charAt(asteriskIndex + 1) != ' ') {
                    modifiedString.append(word, 0, asteriskIndex + 1);
                } else {
                    modifiedString.append(word).append(" ");
                }
            } else {
                modifiedString.append(word).append(" ");
            }
        }
        return modifiedString.toString().trim();
    }
}
