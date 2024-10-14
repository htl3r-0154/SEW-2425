import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CocktailBar {

    public static Set<String> getAvailableDrinks(String file) throws IOException {
        Map<String, Set<String>> drinks = new TreeMap<>();
        Set<String> availableDrinks;

        try (BufferedReader in = Files.newBufferedReader(Paths.get(file), Charset.forName("UTF-8"))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains(":")) {
                    String[] splitLine = line.split(":");
                    String zutat = splitLine[0].trim();
                    String[] splitDrinks = splitLine[1].split(",");

                    for (String drink : splitDrinks) {
                        drink = drink.trim();
                        drinks.computeIfAbsent(drink, k -> new HashSet<>()).add(zutat);
                    }
                } else if (!line.contains("#") && !line.isEmpty()) {
                    String[] zutaten = line.split(",");
                    for (int i = 0; i < zutaten.length; i++) {
                        zutaten[i] = zutaten[i].trim();
                    }
                    for (Set<String> drinkSet : drinks.values()) {
                        for (String zutat : zutaten){
                            if(drinkSet.contains(zutat)){
                                drinkSet.remove(zutat);
                            }
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<String, Set<String>>> iterator = drinks.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            if (!entry.getValue().isEmpty()) {
                iterator.remove();
            }
        }
        availableDrinks = drinks.keySet();
        return availableDrinks;
    }
}