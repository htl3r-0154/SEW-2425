import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {

    public static String removeMultiSpaces(String s) {
        return s.replaceAll(" +", " ");
    }

    public static String[] splitToLines(String s) {
        return s.split("\\r?\\n|\\r");
    }

    static List<String> splitToLines(String s, int maxLen) {
        List<String> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\S+(\\s+|$)");
        Matcher matcher = pattern.matcher(s);

        StringBuilder currentLine = new StringBuilder();

        while (matcher.find()) {
            String word = matcher.group();
            if (currentLine.length() + word.trim().length() > maxLen) {
                if (!currentLine.isEmpty()) {
                    lines.add(currentLine.toString().stripTrailing());
                    currentLine.setLength(0);
                }
            }

            currentLine.append(word);
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString().stripTrailing());
        }

        return lines;
    }
}
