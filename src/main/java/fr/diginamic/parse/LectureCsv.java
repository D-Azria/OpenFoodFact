package fr.diginamic.parse;

import fr.diginamic.App;
import fr.diginamic.entites.OpenFoodFactProductData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class LectureCsv {

    public static List lire(String nomFichierCsv){
        //OpenFoodFactProductData produitsData = new OpenFoodFactProductData();

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

        lines.set(3129, lines.get(3129).replace("issus de |’Agriculture", "issus de l’Agriculture"));
        lines.set(6375, lines.get(6375).replace(" stéaroyl-2—|acty|ate de sodium", "stéaroyl-2—lactylate de sodium"));
        lines.set(6610, lines.get(6610).replace(" conservateur |antioxydant", "conservateur antioxydant"));
        lines.set(8428, lines.get(8428).replace(" l,|% ", ""));
        lines.set(8990, lines.get(8990).replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Alaska"));
        lines.set(10070, lines.get(10070).replace("jaune d`oeuf", "jaune d'oeuf"));
        lines.set(10070, lines.get(10070).replace("de |’œuf", "de l'oeuf"));
        lines.set(11181, lines.get(11181).replace("moutarde à |’ancienne", "moutarde à l’ancienne"));
        System.out.println("Après modif : "+lines.get(3129));
        System.out.println("Après modif : "+lines.get(6375));
        System.out.println("Après modif : "+lines.get(6610));
        System.out.println("Après modif : "+lines.get(8428));
        System.out.println("Après modif : "+lines.get(8990));
        System.out.println("AVANT modif : "+lines.get(10070));
        System.out.println("Après modif : "+lines.get(11181));

        lines.remove(0);
        return lines;
    }

}
