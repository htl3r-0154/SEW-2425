import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class WinnerTest {

    @Test
    void testWinnersOfToursLessThan3500km_1() {
        // a) Ermittle eine Liste mit den Namen aller Gewinner mit Touren, die kleiner als 3500km sind.
        List<String> expected = List.of("Alberto Contador", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Chris Froome");
        List<String> actual = Winner.getWinnersOfToursLessThan3500km(Winner.tdfWinners);
        assertEquals(expected, actual);
    }

    @Test
    void testWinnersOfToursLessThan3500km_2() {
        // a) Ermittle eine Liste mit den Namen aller Gewinner mit Touren, die kleiner als 3500km sind.
        List<String> expected = List.of("Chris Froyooyome10");
        List<String> actual = Winner.getWinnersOfToursLessThan3500km(Winner.tdfWinnersX);
        assertEquals(expected, actual);
    }

    @Test
    void testWinnersOfToursGreaterThan3500km_1() {
        // b) Ermittle eine Liste mit den Namen aller Gewinner mit Touren, die größer als 3500km sind.
        List<String> expected = List.of("Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck",
                "Vincenzo Nibali", "Chris Froome");
        List<String> actual = Winner.getWinnersOfToursGreaterThan3500km(Winner.tdfWinners);
        assertEquals(expected, actual);
    }

    @Test
    void testWinnersOfToursGreaterThan3500km_2() {
        // b) Ermittle eine Liste mit den Namen aller Gewinner mit Touren, die größer als 3500km sind.
        List<String> expected = List.of("Óscaxar Pereiroyo0", "Albertoyo Coyontaxadoyor1", "Caxarloyos Saxastre2",
                "Albertoyo Coyontaxadoyor3", "Andy Schleck4", "Caxadel Evaxans5", "Braxadley Wiggins6",
                "Chris Froyooyome7", "Vincenzoyo Nibaxali8", "Chris Froyooyome9");
        List<String> actual = Winner.getWinnersOfToursGreaterThan3500km(Winner.tdfWinnersX);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstTwoWinnersOfToursLessThan3500km_1() {
        // c) Ermittle eine Winner-Object-Liste mit den ersten beiden Gewinnern mit Touren, die kleiner als 3500km
        // sind.
        List<Winner> expected = List.of(Winner.tdfWinners.get(3), Winner.tdfWinners.get(5));
        List<Winner> actual = Winner.getFirstTwoWinnersOfToursLessThan3500km(Winner.tdfWinners);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstTwoWinnersOfToursLessThan3500km_2() {
        // c) Ermittle eine Winner-Object-Liste mit den ersten beiden Gewinnern mit Touren, die kleiner als 3500km
        // sind.
        List<Winner> expected = List.of(Winner.tdfWinnersX.get(10));
        List<Winner> actual = Winner.getFirstTwoWinnersOfToursLessThan3500km(Winner.tdfWinnersX);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstTwoWinnersNamesOfToursLessThan3500km_1() {
        // d) Ermittle eine Liste mit allen Namen mit den ersten beiden Gewinnern mit Touren, die kleiner als 3500km
        // sind.
        List<String> expected = List.of("Alberto Contador", "Cadel Evans");
        List<String> actual = Winner.getFirstTwoWinnersNamesOfToursLessThan3500km(Winner.tdfWinners);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstTwoWinnersNamesOfToursLessThan3500km_2() {
        // d) Ermittle eine Liste mit allen Namen mit den ersten beiden Gewinnern mit Touren, die kleiner als 3500km
        // sind.
        List<String> expected = List.of("Chris Froyooyome10");
        List<String> actual = Winner.getFirstTwoWinnersNamesOfToursLessThan3500km(Winner.tdfWinnersX);
        assertEquals(expected, actual);
    }

    @Test
    void testDistinctTDFWinners_1() {
        // e) Ermittle eine Liste mit den Namen aller Gewinner von mindestens einer Tour. Kein Name darf doppelt
        // vorkommen.
        List<String> expected = List.of("Óscar Pereiro", "Alberto Contador", "Carlos Sastre", "Andy Schleck", "Cadel Evans", "Bradley Wiggins", "Chris Froome", "Vincenzo Nibali");
        List<String> actual = Winner.getDistinctTDFWinners(Winner.tdfWinners);
        assertEquals(expected, actual);
    }

    @Test
    void testDistinctTDFWinners_2() {
        // e) Ermittle eine Liste mit den Namen aller Gewinner von mindestens einer Tour. Kein Name darf doppelt
        // vorkommen.
        List<String> expected = List.of("Óscaxar Pereiroyo0", "Albertoyo Coyontaxadoyor1",
                "Caxarloyos Saxastre2", "Albertoyo Coyontaxadoyor3",
                "Andy Schleck4", "Caxadel Evaxans5", "Braxadley Wiggins6", "Chris Froyooyome7",
                "Vincenzoyo Nibaxali8", "Chris Froyooyome9", "Chris Froyooyome10");
        List<String> actual = Winner.getDistinctTDFWinners(Winner.tdfWinnersX);
        assertEquals(expected, actual);
    }

    @Test
    void testDistinctTDFWinnersCount_1() {
        // f) Ermittle die Anzahl aller Gewinner von mindestens einer Tour. (Zähle Gewinner nicht doppelt, wenn sie
        // mehrere Touren gewonnen haben.)
        Long actual = Winner.getDistinctTDFWinnersCount(Winner.tdfWinners);
        assertEquals(8, actual);
    }

    @Test
    void testDistinctTDFWinnersCount_2() {
        // f) Ermittle die Anzahl aller Gewinner von mindestens einer Tour. (Zähle Gewinner nicht doppelt, wenn sie
        // mehrere Touren gewonnen haben.)
        Long actual = Winner.getDistinctTDFWinnersCount(Winner.tdfWinnersX);
        assertEquals(11, actual);
    }

    @Test
    void testGetAllButFirstTwo_1() {
        // g) Ermittle eine Winner-Object-Liste, die alle bis auf die ersten beiden Elemente von »tdfWinners« enthält.
        List<Winner> actual = Winner.getAllButFirstTwo(Winner.tdfWinners);
        List<Winner> expected = Winner.tdfWinners.subList(2, Winner.tdfWinners.size());
        assertEquals(expected, actual);
    }

    @Test
    void testGetAllButFirstTwo_2() {
        // g) Ermittle eine Winner-Object-Liste, die alle bis auf die ersten beiden Elemente von »tdfWinners« enthält.
        List<Winner> actual = Winner.getAllButFirstTwo(Winner.tdfWinnersX);
        List<Winner> expected = Winner.tdfWinnersX.subList(2, Winner.tdfWinnersX.size());
        assertEquals(expected, actual);
    }

    @Test
    void testGetYearAndWinner_1() {
        // h) Ermittle eine String-Liste mit der Info: »Jahr - Gewinner«, …
        List<String> actual = Winner.getYearAndWinner(Winner.tdfWinners);
        List<String> expected = List.of(
                "2006 - Óscar Pereiro", "2007 - Alberto Contador", "2008 - Carlos Sastre",
                "2009 - Alberto Contador", "2010 - Andy Schleck", "2011 - Cadel Evans",
                "2012 - Bradley Wiggins", "2013 - Chris Froome", "2014 - Vincenzo Nibali",
                "2015 - Chris Froome", "2016 - Chris Froome"
        );
        assertEquals(expected, actual);
    }

    @Test
    void testGetYearAndWinner_2() {
        // h) Ermittle eine String-Liste mit der Info: »Jahr - Gewinner«
        List<String> actual = Winner.getYearAndWinner(Winner.tdfWinnersX);
        List<String> expected = List.of("2006 - Óscaxar Pereiroyo0", "2007 - Albertoyo Coyontaxadoyor1",
                "2002 - Caxarloyos Saxastre2", "2009 - Albertoyo Coyontaxadoyor3", "2010 - Andy Schleck4",
                "2011 - Caxadel Evaxans5", "2012 - Braxadley Wiggins6", "2017 - Chris Froyooyome7",
                "2014 - Vincenzoyo Nibaxali8", "2015 - Chris Froyooyome9",
                "2016 - Chris Froyooyome10");
        assertEquals(expected, actual);
    }


    @Test
    void testGetNameLengths_1() {
        List<Integer> actual = Winner.getNameLengths(Winner.tdfWinners);
        List<Integer> expected = List.of(13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12);
        assertEquals(expected, actual);
    }

    @Test
    void testGetNameLengths_2() {
        // i) Ermittle eine Integer-Liste mit allen Längen der Namen.
        List<Integer> actual = Winner.getNameLengths(Winner.tdfWinnersX);
        List<Integer> expected = List.of(18, 25, 20, 25, 13, 16, 18, 17, 20, 17, 18);
        assertEquals(expected, actual);
    }

    @Test
    void testGetFirstWinningYearByName_1() {
        // j) Ermittle irgendein (das erste) Sieger-Jahr von “Wiggins”.
        Optional<Integer> actual = Winner.getFirstWinningYearByName(Winner.tdfWinners, "Wiggins");
        assertEquals(Optional.of(2012), actual);

        Optional<Integer> notFound = Winner.getFirstWinningYearByName(Winner.tdfWinners, "Nonexistent");
        assertTrue(notFound.isEmpty());
    }

    @Test
    void testGetFirstWinningYearByName_2() {
        // j) Ermittle irgendein (das erste) Sieger-Jahr von “Wiggins”.
        Optional<Integer> actual = Winner.getFirstWinningYearByName(Winner.tdfWinnersX, "Nibaxali8");
        assertEquals(Optional.of(2014), actual);

        Optional<Integer> notFound = Winner.getFirstWinningYearByName(Winner.tdfWinnersX, "Nonexistent");
        assertTrue(notFound.isEmpty());
    }

    @Test
    void testGetWinnerOfYear_1() {
        // k) Ermittle den Gewinner des Jahres 2014.
        String actual = Winner.getWinnerOfYear(Winner.tdfWinners, 2014);
        String expected = "Vincenzo Nibali";
        assertEquals(expected, actual);
    }

    @Test
    void testGetWinnerOfYear_2() {
        // k) Ermittle den Gewinner des Jahres 2014.
        String actual = Winner.getWinnerOfYear(Winner.tdfWinnersX, 2014);
        String expected = "Vincenzoyo Nibaxali8";
        assertEquals(expected, actual);
    }

    @Test
    void testGetWinnerOfYear_3() {
        // k) Ermittle den Gewinner des Jahres 2014.
        String actual = Winner.getWinnerOfYear(Winner.tdfWinners, 2039);
        String expected = "No winner found for the specified year";
        assertEquals(expected, actual);
    }

    @Test
    void testGetWinnerOfYear_4() {
        // k) Ermittle den Gewinner des Jahres 2014.
        String actual = Winner.getWinnerOfYear(Winner.tdfWinners, 2010);
        String expected = "Andy Schleck";
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalLengthOfTours_1() {
        // l) Ermittle die Gesamtlänge aller Touren.
        double actual = Winner.getTotalLengthOfTours(Winner.tdfWinners);
        assertEquals(38767.0, actual, 1);
    }

    @Test
    void testGetTotalLengthOfTours_2() {
        // l) Ermittle die Gesamtlänge aller Touren.
        double actual = Winner.getTotalLengthOfTours(Winner.tdfWinnersX);
        assertEquals(78207.0, actual, 1);
    }

    @Test
    void testGetShortestTourLength_1() {
        // m) Ermittle die Länge der kürzesten Tour.
        double actual = Winner.getShortestTourLength(Winner.tdfWinners);
        assertEquals(3360.0, actual, 1);
    }

    @Test
    void testGetShortestTourLength_2() {
        // m) Ermittle die Länge der kürzesten Tour.
        double actual = Winner.getShortestTourLength(Winner.tdfWinnersX);
        assertEquals(2529.0, actual, 1);
    }

    @Test
    void testLongestDistance_1() {
        // n) Ermittle die Länge der längsten Tour.
        Long actual = Winner.getLongestDistance(Winner.tdfWinners);
        assertEquals(3661, actual);
    }

    @Test
    void testLongestDistance_2() {
        // n) Ermittle die Länge der längsten Tour.
        Long actual = Winner.getLongestDistance(Winner.tdfWinnersX);
        assertEquals(7760, actual);
    }

    @Test
    void testGetWinnerWithHighestAverageSpeed_1() {
        // o) Ermittle den Gewinner mit der höchsten Durchschnitts-Geschwindigkeit.
        Optional<Winner> actual = Winner.getWinnerWithHighestAverageSpeed(Winner.tdfWinners);
        assertTrue(actual.isPresent());
        assertEquals("Óscar Pereiro", actual.get().getName());
    }

    @Test
    void testGetWinnerWithHighestAverageSpeed_2() {
        // o) Ermittle den Gewinner mit der höchsten Durchschnitts-Geschwindigkeit.
        Optional<Winner> actual = Winner.getWinnerWithHighestAverageSpeed(Winner.tdfWinnersX);
        assertTrue(actual.isPresent());
        assertEquals("Chris Froyooyome9", actual.get().getName());
    }

    @Test
    void testGetWinnerWithHighestAverageSpeed_EmptyList() {
        // o) Ermittle den Gewinner mit der höchsten Durchschnitts-Geschwindigkeit.
        Optional<Winner> actual = Winner.getWinnerWithHighestAverageSpeed(Collections.emptyList());
        assertTrue(actual.isEmpty());
    }

    @Test
    void testFastestTDF_1() {
        // p) Ermittle die höchste Durchschnitts-Geschwindigkeit.
        double actual = Winner.getFastestTDFWinner(Winner.tdfWinners).get().getAveSpeed();
        assertEquals(40.78, actual, 0.1);
    }

    @Test
    void testFastestTDF_2() {
        // p) Ermittle die höchste Durchschnitts-Geschwindigkeit.
        double actual = Winner.getFastestTDFWinner(Winner.tdfWinnersX).get().getAveSpeed();
        assertEquals(313.27, actual, 0.1);
    }

    @Test
    void testGetAllTeamsCommaSeparated_1() {
        // q) Erzeuge einen String, der für jedes Element von »tdfWinners« das Team enthält. Die einzelnen Teams
        // sollen mit einem Beistrich von einander getrennt sein.
        String actual = Winner.getAllTeamsCommaSeparated(Winner.tdfWinners);
        String expected = "Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, " +
                "BMC Racing Team, Team Sky";
        assertEquals(expected, actual);
    }

    @Test
    void testGetAllTeamsCommaSeparated_2() {
        // q) Erzeuge einen String, der für jedes Element von »tdfWinners« das Team enthält. Die einzelnen Teams
        // sollen mit einem Beistrich von einander getrennt sein.
        String actual = Winner.getAllTeamsCommaSeparated(Winner.tdfWinnersX);
        String expected = "Caxaisse d'Epaxargne–Illes Baxaleaxars, Discoyovery Chaxannel, Teaxam CSC, Astaxanaxa, " +
                "Teaxam Saxaxoyo Baxank, BMC Raxacing Teaxam, Teaxam Sky";
        assertEquals(expected, actual);
    }

    @Test
    void testWinsByNationalityCounting_4() {
        // r) Erzeuge eine Map, die für jede Nationaltität eine Liste mit ihren Winner-Objekten enthält.
        Map<String, List<Winner>> actual = Winner.groupWinnersByNationality(Winner.tdfWinners);
        String actualString = actual.toString();
        String expected = "{Great Britain=[Bradley Wiggins, Chris Froome, Chris Froome, Chris Froome], "
                + "Luxembourg=[Andy Schleck], Italy=[Vincenzo Nibali], Australia=[Cadel Evans], "
                + "Spain=[Óscar Pereiro, Alberto Contador, Carlos Sastre, Alberto Contador]}";
        assertEquals(expected, actualString);
    }

    @Test
    void testWinsByNationalityCounting_5() {
        // r) Erzeuge eine Map, die für jede Nationaltität eine Liste mit ihren Winner-Objekten enthält.
        Map<String, List<Winner>> actual = Winner.groupWinnersByNationality(Winner.tdfWinnersX);
        String actualString = actual.toString();
        String expected = "{Spaxain=[Óscaxar Pereiroyo0, Albertoyo Coyontaxadoyor1, Caxarloyos Saxastre2, "
            + "Albertoyo Coyontaxadoyor3], Greaxat Britaxain=[Braxadley Wiggins6, Chris Froyooyome7, "
            + "Chris Froyooyome9, Chris Froyooyome10], Austraxaliaxa=[Caxadel Evaxans5], Luxemboyourg=[Andy Schleck4], "
            + "Itaxaly=[Vincenzoyo Nibaxali8]}";
        assertEquals(expected, actualString);
    }

    @Test
    void testWinsByNationalityCounting_1() {
        // s) Erzeuge eine Map, die für jede Nationaltität die Anzahl ihrer Siege enthält.
        double actual = Winner.winsByNationalityCounting(Winner.tdfWinners).get("Italy");
        assertEquals(1, actual);
    }

    @Test
    void testWinsByNationalityCounting_2() {
        // s) Erzeuge eine Map, die für jede Nationaltität die Anzahl ihrer Siege enthält.
        double actual = Winner.winsByNationalityCounting(Winner.tdfWinners).get("Great Britain");
        assertEquals(4, actual);
    }

    @Test
    void testWinsByNationalityCounting_3() {
        // s) Erzeuge eine Map, die für jede Nationaltität die Anzahl ihrer Siege enthält.
        double actual = Winner.winsByNationalityCounting(Winner.tdfWinnersX).get("Itaxaly");
        assertEquals(1, actual);
    }
}
