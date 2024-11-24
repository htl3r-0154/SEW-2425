import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringToolsTestV2 {

  @Test
  void testRemoveMultiSpaces() {
    assertEquals("abc def", StringTools.removeMultiSpaces("abc     def"));
    assertEquals(" a b c", StringTools.removeMultiSpaces("   a b  c"));
    assertEquals("a b c", StringTools.removeMultiSpaces("a  b   c"));
    assertEquals("a b", StringTools.removeMultiSpaces("a       b"));
    assertEquals(" a b c ", StringTools.removeMultiSpaces("   a b  c   "));
    assertEquals("abc", StringTools.removeMultiSpaces("abc"));
  }

  @Test
  void testSplitToLines1() {
    assertEquals(1, StringTools.splitToLines("").length);
    assertEquals(5, StringTools.splitToLines("Hallo\nWelt\r\nWie\rgeht\nes?").length);
    assertEquals("dfbsdfhdfbndegbhrbfdhrgfbdrh", StringTools.splitToLines("dfbsdfhdfbndegbhrbfdhrgfbdrh\r\ndfsfskjfnjsdndsndsjhjsdbhdbh\r\n")[0]);
    assertEquals("dfsfskjfnjsdndsndsjhjsdbhdbh", StringTools.splitToLines("dfbsdfhdfbndegbhrbfdhrgfbdrh\r\ndfsfskjfnjsdndsndsjhjsdbhdbh\r\n")[1]);
  }

  @Test
  void testSplitToLines2() {
    String text = "Dies ist ein Beispiel";
    int maxLength = 10;

    List<String> result = StringTools.splitToLines(text, maxLength);

    assertEquals(3, result.size());
    assertEquals("Dies ist", result.get(0));
    assertEquals("ein", result.get(1));
    assertEquals("Beispiel", result.get(2));
  }

  @Test
  void testSplitToLines3() {
    String text = "Schreibe die Methode static List<String> splitToLines(String s, int maxLen) die s so " +
            "umwandelt, dass Zeilen mit maximal maxLen Zeichen entstehen.";
    int maxLength = 20;

    List<String> result = StringTools.splitToLines(text, maxLength);

    assertEquals(8, result.size());
    assertEquals("Schreibe die Methode", result.get(0));
    assertEquals("static List<String>", result.get(1));
    assertEquals("splitToLines(String", result.get(2));
    assertEquals("s, int maxLen) die s", result.get(3));
    assertEquals("so umwandelt, dass", result.get(4));
    assertEquals("Zeilen mit maximal", result.get(5));
    assertEquals("maxLen Zeichen", result.get(6));
    assertEquals("entstehen.", result.get(7));

  }


  @Test
  void testSplitToLines4() {
    String text = "Dies  ist   ein   Text   mit   mehreren   Leerzeichen.";
    int maxLength = 15;

    List<String> result = StringTools.splitToLines(text, maxLength);

    assertEquals(4, result.size());
    assertEquals("Dies  ist   ein", result.get(0));
    assertEquals("Text   mit", result.get(1));
    assertEquals("mehreren", result.get(2));
    assertEquals("Leerzeichen.", result.get(3));
  }

  @Test
  void testSplitToLines5() {
    String text = "";
    int maxLength = 10;
    List<String> result = StringTools.splitToLines(text, maxLength);
    assertTrue(result.isEmpty());
  }

}
