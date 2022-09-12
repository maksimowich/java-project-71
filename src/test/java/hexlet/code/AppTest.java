package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.nio.file.Paths;
import java.nio.file.Files;

class AppTest {

    private static final String RESOURCE_DIRECTORY = "src/test/resources";
    private static final String FILE1 = "file1.json";
    private static final String FILE2 = "file2.json";
    private static final String FILE3 = "file1.yml";
    private static final String FILE4 = "file2.yml";
    private static final String RESULT1 = "resultDiff";
    private static final String RESULT2 = "resultPlainDiff";
    private static final String RESULT3 = "resultJsonDiff";

    private static String expected;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    static void preparing() throws Exception {
        String pathToResult = Paths.get(RESOURCE_DIRECTORY, RESULT1).toFile().getAbsolutePath();
        String pathToPlainResult = Paths.get(RESOURCE_DIRECTORY, RESULT2).toFile().getAbsolutePath();
        String pathToJsonResult = Paths.get(RESOURCE_DIRECTORY, RESULT3).toFile().getAbsolutePath();

        expected = Files.readString(Paths.get(pathToResult));
        expectedPlain = Files.readString(Paths.get(pathToPlainResult));
        expectedJson = Files.readString(Paths.get(pathToJsonResult));
    }

    @Test
    @DisplayName("Run app with json testfiles")
    void jsonTest() throws Exception {
        String filePath1 = Paths.get(RESOURCE_DIRECTORY, FILE1).toFile().getAbsolutePath();
        String filePath2 = Paths.get(RESOURCE_DIRECTORY, FILE2).toFile().getAbsolutePath();
        assertEquals(expected, Differ.generate(filePath1, filePath2, "stylish"));
    }

    @Test
    @DisplayName("Run app with yaml testfiles")
    void yamlTest() throws Exception {
        String filePath3 = Paths.get(RESOURCE_DIRECTORY, FILE3).toFile().getAbsolutePath();
        String filePath4 = Paths.get(RESOURCE_DIRECTORY, FILE4).toFile().getAbsolutePath();
        assertEquals(expected, Differ.generate(filePath3, filePath4, "stylish"));
    }

    @Test
    @DisplayName("Run app with plain formatter")
    void formatterPlainTest() throws Exception {
        String filePath1 = Paths.get(RESOURCE_DIRECTORY, FILE1).toFile().getAbsolutePath();
        String filePath2 = Paths.get(RESOURCE_DIRECTORY, FILE2).toFile().getAbsolutePath();
        assertEquals(expectedPlain, Differ.generate(filePath1, filePath2, "plain"));
    }

    @Test
    @DisplayName("Run app with json formatter")
    void formatterJsonTest() throws Exception {
        String filePath1 = Paths.get(RESOURCE_DIRECTORY, FILE1).toFile().getAbsolutePath();
        String filePath2 = Paths.get(RESOURCE_DIRECTORY, FILE2).toFile().getAbsolutePath();
        assertEquals(expectedJson, Differ.generate(filePath1, filePath2, "json"));
    }

    @Test
    @DisplayName("Run app with default formatter")
    void defaultFormatterTest() throws Exception {
        String filePath1 = Paths.get(RESOURCE_DIRECTORY, FILE1).toFile().getAbsolutePath();
        String filePath2 = Paths.get(RESOURCE_DIRECTORY, FILE2).toFile().getAbsolutePath();
        assertEquals(expected, Differ.generate(filePath1, filePath2));
    }
}
