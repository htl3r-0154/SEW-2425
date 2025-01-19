import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Winner {
    private int year;
    private String nationality;
    private String name;
    private String team;
    private int lengthKm;
    private Duration winningTime;
    private int stageWins;
    private int daysInYellow;


    public static final List<Winner> tdfWinners = Arrays.asList(
            new Winner(2006, "Spain", "Óscar Pereiro", "Caisse d'Epargne–Illes Balears", 3657, Duration.parse("PT89H40M27S"), 8),
            new Winner(2007, "Spain", "Alberto Contador", "Discovery Channel", 3570, Duration.parse("PT91H00M26S"), 4),
            new Winner(2008, "Spain", "Carlos Sastre", "Team CSC", 3559, Duration.parse("PT87H52M52S"), 5),
            new Winner(2009, "Spain", "Alberto Contador", "Astana", 3459, Duration.parse("PT85H48M35S"), 7),
            new Winner(2010, "Luxembourg", "Andy Schleck", "Team Saxo Bank", 3642, Duration.parse("PT91H59M27S"), 12),
            new Winner(2011, "Australia", "Cadel Evans", "BMC Racing Team", 3430, Duration.parse("PT86H12M22S"), 2),
            new Winner(2012, "Great Britain", "Bradley Wiggins", "Team Sky", 3496, Duration.parse("PT87H34M47S"), 14),
            new Winner(2013, "Great Britain", "Chris Froome", "Team Sky", 3404, Duration.parse("PT83H56M20S"), 14),
            new Winner(2014, "Italy", "Vincenzo Nibali", "Astana", 3661, Duration.parse("PT89H59M06S"), 19),
            new Winner(2015, "Great Britain", "Chris Froome", "Team Sky", 3360, Duration.parse("PT84H46M14S"), 16),
            new Winner(2016, "Great Britain", "Chris Froome", "Team Sky", 3529, Duration.parse("PT89H04M48S"), 14)
    );


    // dummy data
    public static final List<Winner> tdfWinnersX = Arrays.asList(
            new Winner(2006, "Spaxain", "Óscaxar Pereiroyo0", "Caxaisse d'Epaxargne–Illes Baxaleaxars", 7657, Duration.parse("PT29H40M27S"), 2),
            new Winner(2007, "Spaxain", "Albertoyo Coyontaxadoyor1", "Discoyovery Chaxannel", 7570, Duration.parse("PT91H00M26S"), 4),
            new Winner(2002, "Spaxain", "Caxarloyos Saxastre2", "Teaxam CSC", 7559, Duration.parse("PT27H52M52S"), 5),
            new Winner(2009, "Spaxain", "Albertoyo Coyontaxadoyor3", "Astaxanaxa", 7459, Duration.parse("PT25H42M75S"), 7),
            new Winner(2010, "Luxemboyourg", "Andy Schleck4", "Teaxam Saxaxoyo Baxank", 7642, Duration.parse("PT91H59M27S"), 12),
            new Winner(2011, "Austraxaliaxa", "Caxadel Evaxans5", "BMC Raxacing Teaxam", 7470, Duration.parse("PT26H12M22S"), 2),
            new Winner(2012, "Greaxat Britaxain", "Braxadley Wiggins6", "Teaxam Sky", 7496, Duration.parse("PT27H74M47S"), 14),
            new Winner(2017, "Greaxat Britaxain", "Chris Froyooyome7", "Teaxam Sky", 7404, Duration.parse("PT27H56M20S"), 14),
            new Winner(2014, "Itaxaly", "Vincenzoyo Nibaxali8", "Astaxanaxa", 7661, Duration.parse("PT29H59M06S"), 19),
            new Winner(2015, "Greaxat Britaxain", "Chris Froyooyome9", "Teaxam Sky", 7760, Duration.parse("PT24H46M14S"), 16),
            new Winner(2016, "Greaxat Britaxain", "Chris Froyooyome10", "Teaxam Sky", 2529, Duration.parse("PT29H04M42S"), 14)
    );


    public Winner(int year, String nationality, String name, String team, int lengthKm, Duration winningTime, int daysInYellow) {
        this.year = year;
        this.nationality = nationality;
        this.name = name;
        this.team = team;
        this.lengthKm = lengthKm;
        this.winningTime = winningTime;
        this.daysInYellow = daysInYellow;
    }

    public static List<String> getWinnersOfToursLessThan3500km(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .filter(winner -> winner.getLengthKm() < 3500)
                .map(Winner::getName)
                .collect(Collectors.toList());
    }


    public static List<String> getWinnersOfToursGreaterThan3500km(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .filter(winner -> winner.getLengthKm() > 3500)
                .map(Winner::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getFirstTwoWinnersNamesOfToursLessThan3500km(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .filter(winner -> winner.getLengthKm() < 3500)
                .map(Winner::getName)
                .limit(2)
                .collect(Collectors.toList());
    }

    public static List<Winner> getFirstTwoWinnersOfToursLessThan3500km(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .filter(winner -> winner.getLengthKm() < 3500)
                .limit(2)
                .collect(Collectors.toList());
    }

    public static List<String> getDistinctTDFWinners(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .map(Winner::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static Long getDistinctTDFWinnersCount(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .map(Winner::getName)
                .distinct()
                .count();
    }

    public static List<Winner> getAllButFirstTwo(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .skip(2)
                .collect(Collectors.toList());
    }

    public static List<String> getYearAndWinner(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .map(winner -> winner.getYear() + " - " + winner.getName())  // Kombiniert Jahr und Namen
                .collect(Collectors.toList());  // Sammelt die Ergebnisse in einer Liste
    }

    public static Optional<Integer> getFirstWinningYearByName(List<Winner> tdfWinnersX, String nibaxali8) {
        return tdfWinnersX.stream()
                .filter(winner -> winner.getName().contains(nibaxali8))
                .map(Winner::getYear)
                .findFirst();
    }

    public static List<Integer> getNameLengths(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .map(winner -> winner.getName().length())
                .collect(Collectors.toList());
    }

    public static String getWinnerOfYear(List<Winner> tdfWinners, int i) {
        return tdfWinners.stream()
                .filter(winner -> winner.getYear() == i)
                .map(Winner::getName)
                .findFirst()
                .orElse("No winner found for the specified year");
    }

    public static double getTotalLengthOfTours(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .mapToDouble(Winner::getLengthKm)
                .sum();
    }

    public static double getShortestTourLength(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .mapToDouble(Winner::getLengthKm)
                .min()
                .orElse(0);
    }

    public static Long getLongestDistance(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .mapToLong(Winner::getLengthKm)
                .max()
                .orElse(0);

    }

    public static Optional<Winner> getWinnerWithHighestAverageSpeed(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .max(Comparator.comparingDouble(Winner::getAveSpeed));
    }

    public static Optional<Winner> getFastestTDFWinner(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .max(Comparator.comparingDouble(Winner::getAveSpeed));
    }

    public static String getAllTeamsCommaSeparated(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .map(Winner::getTeam)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public static Map<String, List<Winner>> groupWinnersByNationality(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .collect(Collectors.groupingBy(Winner::getNationality));
    }

    public static Map<String, Long> winsByNationalityCounting(List<Winner> tdfWinners) {
        return tdfWinners.stream()
                .collect(Collectors.groupingBy(Winner::getNationality, Collectors.counting()));
    }



    public double getAveSpeed() {
        return (getLengthKm() / (getWinningTime().getSeconds() / 3600.0));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getLengthKm() {
        return lengthKm;
    }

    public void setLengthKm(int lengthKm) {
        this.lengthKm = lengthKm;
    }

    public Duration getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(Duration winningTime) {
        this.winningTime = winningTime;
    }

    public int getStageWins() {
        return stageWins;
    }

    public void setStageWins(int stageWins) {
        this.stageWins = stageWins;
    }

    public int getDaysInYellow() {
        return daysInYellow;
    }

    public void setDaysInYellow(int daysInYellow) {
        this.daysInYellow = daysInYellow;
    }

    @Override
    public String toString() {
        return name;
    }

    // TBD: Methods for fulfilling the JUnit5-Tests

}
