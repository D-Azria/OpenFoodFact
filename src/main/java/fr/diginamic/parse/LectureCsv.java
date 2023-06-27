package fr.diginamic.parse;

import fr.diginamic.App;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class LectureCsv {

    public static List lire(String nomFichierCsv){

        List<String> lines = new ArrayList<>();
        try{
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream(nomFichierCsv);
            if (inputStream != null){
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    //System.out.println(line);
                }
                reader.close();
                streamReader.close();
                inputStream.close();
            } else {
                System.out.println("File not found!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        lines.set(12, lines.get(12).replace(" Yf±jfiifi PD 2ClFl", ""));
        lines.set(2260, lines.get(2260).replace("Confiture", ", Confiture"));
        lines.set(2161, lines.get(2161).replace("Marmelade", ", Marmelade"));
        lines.set(3129, lines.get(3129).replace("issus de |’Agriculture", "issus de l’Agriculture"));
        lines.set(5446, lines.get(5446).replace("sucre farine de blé oeuf sirop de glucose fructose graisses végétaleslait entier concentré sucré 8.5%humectantbeurre de cacao lait écrémé en poudre 3.2%pâte de cacao lactoserum en poudre de lait beurre concentré émulsifiantalcool sel poudre à leverarômes amidon de froment  peut contenir fruit à coque", "sucre, farine de blé, oeuf, sirop de glucose fructose, graisses végétales, lait entier concentré sucré 8.5%, humectant, beurre de cacao, lait écrémé en poudre 3.2%, pâte de cacao, lactoserum en poudre de lait, beurre concentré, émulsifiant, alcool, sel, poudre à lever, arômes, amidon de froment,  peut contenir fruit à coque"));
        lines.set(6375, lines.get(6375).replace(" stéaroyl-2—|acty|ate de sodium", "stéaroyl-2—lactylate de sodium"));
        lines.set(6610, lines.get(6610).replace(" conservateur |antioxydant", "conservateur antioxydant"));
        lines.set(8428, lines.get(8428).replace(" l,|% ", ""));
        lines.set(8990, lines.get(8990).replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Alaska"));
        lines.set(10070, lines.get(10070).replace("jaune d`oeuf", "jaune d'oeuf"));
        lines.set(10070, lines.get(10070).replace("de |’œuf", "de l'oeuf"));
        lines.set(11181, lines.get(11181).replace("moutarde à |’ancienne", "moutarde à l’ancienne"));

        lines.remove(0);
        return lines;
    }

}
