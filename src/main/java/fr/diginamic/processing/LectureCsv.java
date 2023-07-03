package fr.diginamic.processing;

import fr.diginamic.App;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe fournit une méthode pour lire un fichier CSV.
 */
public class LectureCsv {

    /**
     * Lit le fichier CSV spécifié et renvoie une liste contenant les lignes lues.
     *
     * @param nomFichierCsv le nom du fichier CSV à lire
     * @return une liste contenant les lignes du fichier CSV
     */
    public static List lire(String nomFichierCsv){

        int i =0;
        List<String> lines = new ArrayList<>();
        try{
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream(nomFichierCsv);
            if (inputStream != null){
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    i++;
                    String l = line.replace(" |’"," l’").replace("|acty|ate","lactylate").replace("A|aska", "Alaska").replace(" l,|% ", "").replace(" l,|% ", "");
                    lines.add(l);
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

        // Remplacement de lignes problématiques dès la lecture du csv
        lines.set(12, lines.get(12).replace(" Yf±jfiifi PD 2ClFl", ""));
        lines.set(2260, lines.get(2260).replace("Confiture", ", Confiture"));
        lines.set(2161, lines.get(2161).replace("Marmelade", ", Marmelade"));
        lines.set(5446, lines.get(5446).replace("sucre farine de blé oeuf sirop de glucose fructose graisses végétaleslait entier concentré sucré 8.5%humectantbeurre de cacao lait écrémé en poudre 3.2%pâte de cacao lactoserum en poudre de lait beurre concentré émulsifiantalcool sel poudre à leverarômes amidon de froment  peut contenir fruit à coque", "sucre, farine de blé, oeuf, sirop de glucose fructose, graisses végétales, lait entier concentré sucré 8.5%, humectant, beurre de cacao, lait écrémé en poudre 3.2%, pâte de cacao, lactoserum en poudre de lait, beurre concentré, émulsifiant, alcool, sel, poudre à lever, arômes, amidon de froment,  peut contenir fruit à coque"));
        lines.set(6610, lines.get(6610).replace(" conservateur |antioxydant", "conservateur antioxydant"));

        // Retrait de la première ligne contenant les indicatifs de chaque  colonne
        lines.remove(0);
        return lines;
    }

}
