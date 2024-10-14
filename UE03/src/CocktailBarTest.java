import org.testng.annotations.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CocktailBarTest {

    @TempDir
    Path tempDir;

    private Path createTestFile(String content) throws IOException {
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, content);
        return file;
    }

    @Test
    void testMainCocktailFile() {
        String errorMessage = "";
        try {
            Set<String> availableCocktails = CocktailBar.getAvailableDrinks("resources/CocktailMix.txt");
            errorMessage = "Incorrect number of available cocktails";
            assertEquals(3, availableCocktails.size(), errorMessage);

            errorMessage = "Available cocktail missing from result";
            assertTrue(availableCocktails.contains("Brazilian Sunrise"), errorMessage);
            assertTrue(availableCocktails.contains("Caipirinha"), errorMessage);
            assertTrue(availableCocktails.contains("Malibu Sunrise"), errorMessage);
        } catch (Exception e) {
            fail(errorMessage + ": got Exception " + e.getClass() + ": \"" + e.getMessage() + "\"");
        }
    }

    @Test
    void testShortCocktailFile() {
        String errorMessage = "";
        try {
            Set<String> availableCocktails = CocktailBar.getAvailableDrinks("resources/CocktailMix_short.txt");
            errorMessage = "Incorrect number of available cocktails";
            assertEquals(1, availableCocktails.size(), errorMessage);

            errorMessage = "Available cocktail missing from result";
            assertTrue(availableCocktails.contains("Caipirinha"), errorMessage);
        } catch (Exception e) {
            fail(errorMessage + ": got Exception " + e.getClass() + ": \"" + e.getMessage() + "\"");
        }
    }

    @Test
    void testEmptyFile() {
        String errorMessage = "Cocktail count mismatch";
        try {
            Set<String> availableCocktails = CocktailBar.getAvailableDrinks("resources/empty.txt");
            assertEquals(0, availableCocktails.size(), errorMessage);
        } catch (Exception e) {
            fail(errorMessage + ": got Exception " + e.getClass() + ": \"" + e.getMessage() + "\"");
        }
    }

    /**
     * Tests the file with unknown cocktails.
     */
    @Test
    void testUnknownCocktailFile() {
        String errorMessage = "";
        try {
            Set<String> availableCocktails = CocktailBar.getAvailableDrinks("resources/CocktailMix_unknown.txt");
            errorMessage = "Incorrect number of available cocktails";
            assertEquals(2, availableCocktails.size(), errorMessage);

            errorMessage = "Available cocktail missing from result";
            assertTrue(availableCocktails.contains("Malibu Barbados"), errorMessage);
            assertTrue(availableCocktails.contains("Malibu Margarita"), errorMessage);
        } catch (Exception e) {
            fail(errorMessage + ": got Exception " + e.getClass() + ": \"" + e.getMessage() + "\"");
        }
    }

    @Test
    void testEmptyFile2() throws IOException {
        String content = "";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlyComments() throws IOException {
        String content = "# This is a comment\n# Another comment";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlyAvailableIngredients() throws IOException {
        String content = "vodka, gin, tonic";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlyRecipes() throws IOException {
        String content = "vodka:Vodka Tonic\ngin:Gin Tonic";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleDrinkAvailable() throws IOException {
        String content = "vodka:Vodka Tonic\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testMultipleDrinksAvailable() throws IOException {
        String content = "vodka:Vodka Tonic,Moscow Mule\nginger beer:Moscow Mule\nvodka,ginger beer";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic", "Moscow Mule"), result);
    }

    @Test
    void testPartialIngredientsNotEnough() throws IOException {
        String content = "vodka:Vodka Tonic,Moscow Mule\nginger beer:Moscow Mule\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testCaseInsensitiveIngredients() throws IOException {
        String content = "Vodka:Vodka Tonic\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testExtraSpacesInIngredients() throws IOException {
        String content = "vodka  :  Vodka Tonic  ,  Moscow Mule\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic", "Moscow Mule"), result);
    }

    @Test
    void testEmptyLines() throws IOException {
        String content = "\n\nvodka:Vodka Tonic\n\nvodka\n\n";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testCommentsInBetween() throws IOException {
        String content = "vodka:Vodka Tonic\n# Comment here\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testNumberLines() throws IOException {
        String content = "1\n2\nvodka:Vodka Tonic\n3\nvodka\n4";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testComplexRecipe() throws IOException {
        String content = "vodka:Moscow Mule,Vodka Tonic\n" +
                "ginger beer:Moscow Mule,Dark and Stormy\n" +
                "rum:Dark and Stormy,Mojito\n" +
                "mint:Mojito\n" +
                "lime:Mojito,Moscow Mule\n" +
                "vodka,ginger beer,lime";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Moscow Mule", "Vodka Tonic"), result);
    }

    @Test
    void testNonExistentFile() {
        assertThrows(IOException.class, () ->
                CocktailBar.getAvailableDrinks("nonexistent.txt")
        );
    }

    @Test
    void testMalformedLine() throws IOException {
        String content = "vodka:Vodka Tonic\nmalformed:line:here\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testDuplicateIngredients() throws IOException {
        String content = "vodka:Vodka Tonic\nvodka:Moscow Mule\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic", "Moscow Mule"), result);
    }

    @Test
    void testDuplicateAvailableIngredients() throws IOException {
        String content = "vodka:Vodka Tonic\nvodka\nvodka,vodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka Tonic"), result);
    }

    @Test
    void testLargeNumberOfDrinks() throws IOException {
        Path file = Path.of("resources/large_cocktail_list.txt");
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertFalse(result.isEmpty());
    }

    @Test
    void testSpecialCharactersInNames() throws IOException {
        String content = "vodka:Vodka & Tonic!,Moscow's Mule\nvodka";
        Path file = createTestFile(content);
        Set<String> result = CocktailBar.getAvailableDrinks(file.toString());
        assertEquals(Set.of("Vodka & Tonic!", "Moscow's Mule"), result);
    }
}