import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlStringTools {

    public static Map<String, Double> getHtmlTagStatistic(String s) throws IOException {
        Map<String, Double> out = new HashMap<>();
        String content = Files.readString(Path.of(s));
        String[] split = content.split("\n");

        for (String line : split) {
            String temp = line.split(" ", 2)[0].toLowerCase();
            if (temp.matches("<[^/]*") && !line.isEmpty()) {
                out.compute(temp.substring(1), (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int max = 0;
        for (Map.Entry<String, Double> entry : out.entrySet()) {
            max += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : out.entrySet()) {
            entry.setValue(entry.getValue() / max);
        }
        return out;
    }

    public static Set<String> getAbsolutLinks(String s) throws IOException {
        String[] all = Files.readString(Path.of(s)).split("\n");
        Set<String> a_tags = new HashSet<>();
        Set<String> links = new HashSet<>();
        for (String string : all) {
            if (string.contains("href=\"http://") || string.contains("href=\"https://")) {
                a_tags.add(string);
            }
        }
        for (String a_tag : a_tags) {
            links.add(a_tag.split("\"")[1].split("//")[1]);
        }
        return links;
    }

    public static Set<String> getDomains(String s) throws IOException {
        Set<String> domains = new TreeSet<>();
        String content = Files.readString(Path.of(s));
        String regex = "(https?://.*\\.\\w{1,3}\\w*)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(content);
        System.out.println("Pattern: " + matcher.pattern());

        while (matcher.find()) {
            String domain = matcher.group(1);
            domains.add(domain);
        }
        System.out.println(domains);
        return domains;
    }
}
