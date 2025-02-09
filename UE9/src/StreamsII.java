import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StreamsII {

    public static Stream<BigInteger> fibonacciSequence() {
        return Stream.iterate(new BigInteger[]{BigInteger.ONE, BigInteger.ONE},
                        f -> new BigInteger[]{f[1], f[0].add(f[1])})
                .map(f -> f[0]);
    }

    public static Stream<Path> greatestFiles(Path path, int n) {
        try {
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .sorted((file1, file2) -> {
                        try {
                            return Long.compare(Files.size(file2), Files.size(file1));
                        } catch (IOException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    })
                    .limit(n);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    public static Map<Character, Long> getCharStatistic(List<String> strings) {
        return strings.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private static final Map<Character, String> morseCodeMap = new HashMap<>();

    static {
        morseCodeMap.put('a', ".-");
        morseCodeMap.put('b', "-...");
        morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-..");
        morseCodeMap.put('e', ".");
        morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--.");
        morseCodeMap.put('h', "....");
        morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---");
        morseCodeMap.put('k', "-.-");
        morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--");
        morseCodeMap.put('n', "-.");
        morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--.");
        morseCodeMap.put('q', "--.-");
        morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "...");
        morseCodeMap.put('t', "-");
        morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-");
        morseCodeMap.put('w', ".--");
        morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--");
        morseCodeMap.put('z', "--..");
        morseCodeMap.put('ä', ".-.-");
        morseCodeMap.put('ö', "---.");
        morseCodeMap.put('ü', "..--");
        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");
    }

    public static String toMorseCode(String text) {
        text = text.toLowerCase();
        StringBuilder morseCode = new StringBuilder();

        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (morseCodeMap.containsKey(c)) {
                    morseCode.append("/").append(morseCodeMap.get(c));
                }
            }
            if (i < words.length - 1) {
                morseCode.append("/");
            }
        }
        morseCode.append("///");
        return morseCode.toString();
    }public static String getMostCommonIp(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> line.split(" ")[0])
                    .collect(Collectors.groupingBy(ip -> ip, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }
    }

    public static String getMostCommonPage(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> {
                        String[] parts = line.split(" \"");
                        if (parts.length > 1) {
                            String[] requestParts = parts[1].split(" ");
                            if (requestParts.length > 1) {
                                return requestParts[1];
                            }
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(page -> page, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }
    }

    public static long countDownloadBytes(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> line.split(" "))
                    .filter(parts -> parts.length > 9)
                    .mapToLong(parts -> {
                        try {
                            return Long.parseLong(parts[9]);
                        } catch (NumberFormatException e) {
                            return 0L;
                        }
                    })
                    .sum();
        }
    }

    public static void printStudentStatistics(String filename) {
        Map<String, Integer> abteilungen = new HashMap<>();
        Set<String> klassen = new HashSet<>();
        Map<Integer, Integer> geburtsmonate = new HashMap<>();
        Map<String, Map<LocalDate, Integer>> klassenGeburtstage = new HashMap<>();
        int studentSum = 0;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length < 5) continue;

                String klasse = parts[2];
                LocalDate geburtstag;
                try {
                    geburtstag = LocalDate.parse(parts[3], dateFormatter);
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format: " + parts[3] + " for student in class " + klasse);
                    continue;
                }
                String abteilung = parts[4];

                abteilungen.put(abteilung, abteilungen.getOrDefault(abteilung, 0) + 1);

                klassen.add(klasse);

                int monat = geburtstag.getMonthValue();
                geburtsmonate.put(monat, geburtsmonate.getOrDefault(monat, 0) + 1);

                klassenGeburtstage.putIfAbsent(klasse, new HashMap<>());
                Map<LocalDate, Integer> geburtstageInKlasse = klassenGeburtstage.get(klasse);
                geburtstageInKlasse.put(geburtstag, geburtstageInKlasse.getOrDefault(geburtstag, 0) + 1);

                studentSum++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Anzahl Schüler pro Abteilung:");
        for (Map.Entry<String, Integer> entry : abteilungen.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\nAnzahl Klassen: " + klassen.size());
        double durchschnittlicheKlassengröße = (double) studentSum / klassen.size();
        System.out.printf("\nDurchschnittliche Klassengröße: %.2f%n", durchschnittlicheKlassengröße);
        System.out.println("\nSchüler pro Geburtsmonat:");
        for (int i = 1; i <= 12; i++) {
            System.out.println("Monat " + i + ": " + geburtsmonate.getOrDefault(i, 0));
        }
        long klassenMitGleichenGeburtstagen = klassenGeburtstage.values().stream()
                .filter(map -> map.values().stream().anyMatch(count -> count >= 2))
                .count();
        System.out.println("\nKlassen mit mind. 2 Schülern gleichen Geburtsdatums: " + klassenMitGleichenGeburtstagen);
    }
}