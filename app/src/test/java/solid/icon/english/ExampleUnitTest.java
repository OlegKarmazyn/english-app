package solid.icon.english;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ValidatingTest validatingTest = new ValidatingTest();
    String[] wordsArray = {"words.words", "words#words", "words$words", "words[words",
            "words]words", "words/words"};
    String expectedWords = "words_words";

    @Test
    public void validation_isCorrect() {
        for (String s : wordsArray) {
            assertEquals(expectedWords, validatingTest.validateKey1(s));
            assertEquals(expectedWords, validatingTest.validateKey2(s));
        }

    }

    @Test
    public void main() {
        String inputString = "§§§topicsName$subTopicsName§§";

        // Define a regular expression pattern to match the expected format
        String regexPattern = "§§§(.*?)\\$(.*?)§§§";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regexPattern);

        // Match the inputString against the pattern
        Matcher matcher = pattern.matcher(inputString);

        // Check if the inputString matches the expected format
        if (matcher.matches()) {
            // Extract topicsName and subTopicsName from the matched groups
            String topicsName = matcher.group(1);
            String subTopicsName = matcher.group(2);

            System.out.println("topicsName: " + topicsName);
            System.out.println("subTopicsName: " + subTopicsName);

            // Perform further actions with topicsName and subTopicsName here
        } else {
            System.out.println("Input does not match the expected format.");
        }
    }
}

