package fr.diginamic.utils;

public class RemoveDoubleDots {
    public static String removeDoubleDots(String str) {
        if(str.endsWith(" :")){
            String cleaned = str;
            do {
                cleaned = cleaned.replace(" :","");
                return cleaned;
            } while (cleaned.endsWith(" :"));
        }
        else {
            return str;
        }
    }
}
