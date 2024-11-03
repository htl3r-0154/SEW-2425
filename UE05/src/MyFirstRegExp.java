import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFirstRegExp {

    public static boolean isIP(String s) {
        String[] octett = s.split("\\.");
        if (octett.length == 4) {
            for (int i = 0; i < 4; i++) {
                if(octett[i].length() > 3 || octett[i].isEmpty() || !octett[i].matches("([0-9]|[0-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])")){
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isNumber(String s) {
        return s.matches("^-?(\\d+\\.?\\d*|\\.?\\d+)([eE]-?\\d+)?$");
    }

    public static boolean hasEvenZeros(String number) {
        String split = number.replaceAll("[^0]", "");
        return split.length() % 2 == 0 && number.matches("^[01]*$");
    }

    public static boolean hasNoDoubles(String number) {
        return !number.matches((".*(.)\\1.*")) && number.matches("^[01]*$");
    }

    public static boolean isClassName(String s) {
        return s.matches("^[A-Z][A-Za-z0-9]*(_?[A-Za-z0-9]+)*$");
    }

    public static boolean isDate(String s) {
        return s.matches(" ?[0-9]{1,2}\\. ?[0-9]{1,2}\\. ?(\\d{4}|\\d{2})?");
    }

    public static String getFirstIp(String s) {
        String pattern = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(s);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String getFirstDate(String s) {
        String pattern = " ?[0-9]{1,2}\\. ?[0-9]{1,2}\\. ?(\\d{4}|\\d{2})?";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(s);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}