import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class StreamsIITest {

    @Test
    void testFibonacci0() {
        assertEquals(BigInteger.valueOf(2), StreamsII.fibonacciSequence().skip(2).findFirst().get());
    }

    @Test
    void testFibonacci1() {
        assertEquals(BigInteger.valueOf(10946), StreamsII.fibonacciSequence().skip(20).findFirst().get());
    }

    @Test
    void testFibonacci2() {
        assertEquals(new BigInteger("573147844013817084101"), StreamsII.fibonacciSequence().skip(100).findFirst().get());
    }

    @Test
    void testGreatestFiles1() throws IOException {
        String expect = "..\\resources\\access.log";
        assertEquals(expect, StreamsII.greatestFiles(Paths.get("../resources"), 2).findFirst().get().toString());
    }

    @Test
    void testGreatestFiles2() throws IOException {
        assertFalse(StreamsII.greatestFiles(Paths.get("bad"), 2).findFirst().isPresent());
    }

    @Test
    void testSortNumerically1() {
        List<String> strings = List.of("Java");
        Map<Character, Long> charStatistic = StreamsII.getCharStatistic(strings);
        String expected = "{a=2, v=1, J=1}";
        assertEquals(expected, charStatistic.toString());
    }

    @Test
    void testSortNumerically2() {
        List<String> strings = List.of("Hallo", "Welt", "Java", "Streams");
        Map<Character, Long> charStatistic = StreamsII.getCharStatistic(strings);
        String expected = "{a=4, r=1, s=1, S=1, t=2, e=2, v=1, W=1, H=1, J=1, l=3, m=1, o=1}";
        assertEquals(expected, charStatistic.toString());
    }

    @Test
    void toMorseCode1() {
        String expected = "/.-/.-../.-.././/.-../.././-..././-.//.---/.-/...-/.-///";
        assertEquals(expected, StreamsII.toMorseCode("Alle lieben Java!"));
    }

    @Test
    void toMorseCode2() {
        String expected = "/..../---./.-././/..--/-..././/-/.-.-/--./.-../../-.-./....///";
        assertEquals(expected, StreamsII.toMorseCode("Höre übe täglich"));
    }

    @Test
    void toMorseCode3() {
        String expected = "/.../---/...///";
        assertEquals(expected, StreamsII.toMorseCode("SOS"));
    }

    @Test
    void toMorseCode4() {
        String expected = "/.----/..---/...--/....-/.....///";
        assertEquals(expected, StreamsII.toMorseCode("12345"));
    }

    @Test
    void getMostCommonIp0() throws IOException {
        String mostCommonIp = StreamsII.getMostCommonIp("../resources/access.log");
        assertEquals("10.0.0.114", mostCommonIp);
    }

    @Test
    void getMostCommonPage0() throws IOException {
        String mostCommonPage = StreamsII.getMostCommonPage("../resources/access.log");
        assertEquals("http://au.v4.b1.download.windowsupdate.com/d/updt/2015/12/10586.0.151029-1700.th2_release_cliententerprise_vol_x64fre_de-de_1f6ab64871cebd8f633d8832cf8d5f971700bda9.esd", mostCommonPage);
    }

    @Test
    void countDownloadBytes0() throws IOException {
        long counter = StreamsII.countDownloadBytes("../resources/access.log");
        assertEquals(28336044127L, counter);
    }
}
/**
    @Test
    void printStudentStatistics0() throws IOException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        StreamsII.printStudentStatistics("resources/Schuelerliste_UTF-8.csv");
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Anzahl Schüler pro Abteilung:"));
        assertTrue(output.contains("IT: 669"));
        assertTrue(output.contains("ME: 453"));

        assertTrue(output.contains("Anzahl Klassen: 42"));

        assertTrue(output.contains("Durchschnittliche Klassengröße: 26,71"));

        assertTrue(output.contains("Schüler pro Geburtsmonat:"));
        assertTrue(output.contains("Monat 1: 105")); // Jänner
        assertTrue(output.contains("Monat 2: 112")); // Februar
        assertTrue(output.contains("Monat 3: 76")); // März
        assertTrue(output.contains("Monat 4: 95")); // April
        assertTrue(output.contains("Monat 5: 97")); // Mai

        assertTrue(output.contains("Klassen mit mind. 2 Schülern gleichen Geburtsdatums: 17"));
    }
}
*/