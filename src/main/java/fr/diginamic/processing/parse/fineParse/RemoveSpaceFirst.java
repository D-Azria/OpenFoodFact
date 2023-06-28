package fr.diginamic.processing.parse.fineParse;

public class RemoveSpaceFirst {
    public static String removeSpaceFirst(String str) {
        if(str.startsWith(" ")){
            String cleaned = str;
            do {
                cleaned = cleaned.replaceFirst(" ","");
                return cleaned;
            } while (cleaned.startsWith(" "));
        }
        else {
            return str;
        }
    }
}
