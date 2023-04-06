package solid.icon.english;

import org.junit.Test;

import static org.junit.Assert.*;

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
}

