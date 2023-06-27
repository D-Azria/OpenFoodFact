package fr.diginamic.utils;

public class RemoveLastSpaces {
    public static String removeLastSpaces(String str) {
        if(str.endsWith(" ")){
            String cleaned = str;
            do {
                cleaned = cleaned.replace(" ","");
                return cleaned;
            } while (cleaned.endsWith(" "));
        }
        else {
            return str;
        }
    }
}
