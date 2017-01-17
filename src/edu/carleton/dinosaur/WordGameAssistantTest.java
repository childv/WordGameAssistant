/**
 * Veronica Child, Adam Tigar, & Liv Phillips
 */

package edu.carleton.dinosaur;
// Added
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

public class WordGameAssistantTest {
    private WordGameAssistant wordGameAssistant;

    @org.junit.Before
    public void setUp() throws Exception {
            this.wordGameAssistant = new WordGameAssistant("dictionary.txt");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        this.wordGameAssistant = null;
    }
    
    @org.junit.Test
    public void wordsUsingOnlyLettersTypical() throws Exception {
        List<String> expectedValue = Arrays.asList("BAD", "BED", "BEAD", "ABED", "BADE", "AD", "BE", "AB", "BA", "DE", "AE", "DEB");
        List<String> actualValue = this.wordGameAssistant.wordsUsingOnlyLetters("edab");
        // Since methods are placement-sensitive, alphabetize lists to ensure match
        sort(expectedValue);
        sort(actualValue);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingOnlyLettersTooMany() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter eight or fewer letters.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingOnlyLetters("cdabeftro");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingOnlyLettersInvalid() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingOnlyLetters("abc3f");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingOnlyLettersEmpty() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a non-empty string.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingOnlyLetters("");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingOnlyLettersNonLetter() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingOnlyLetters("[34]abx&");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingLettersAllowingRepetitionTypical() throws Exception {
        List<String> expectedValue = Arrays.asList("tillite", "little, ", "illite", "title", "lite", "elite",
                "titi", "tell", "tile", "leet", "teel", "tele", "tet", "til", "ill", "lit", "tit", "tie", "lie",
                "lei", "tee", "tel", "eel", "let", "ell", "lee", "it", "ti", "el", "et", "li");
        List<String> actualValue = this.wordGameAssistant.wordsUsingLettersAllowingRepetition("ilte");
        for (int i=0; i < expectedValue.size(); i++) {
            String x = expectedValue.get(i).toUpperCase();
             expectedValue.set(i, x);
        }
        sort(expectedValue);
        sort(actualValue);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingLettersAllowingRepetitionTooMany() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter eight or fewer letters.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingLettersAllowingRepetition("cdabeftro");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingLettersAllowingRepetitionInvalid() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingLettersAllowingRepetition("abc3f");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingLettersAllowingRepetitionEmpty() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a non-empty string.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingLettersAllowingRepetition("");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingLettersAllowingRepetitionNonLetter() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingLettersAllowingRepetition("9dfjk7[]=+");
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void wordsUsingAllLettersTypical() throws Exception {
        List<String> expectedValue = Arrays.asList("MANE", "NAME", "MEAN", "AMEN", "NEMA");
        List<String> actualValue = this.wordGameAssistant.wordsUsingAllLetters("aemn");
        sort(expectedValue);
        sort(actualValue);
        assertEquals(expectedValue, actualValue);
    }


    @org.junit.Test
    public void wordsUsingAllLettersTooMany() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter eight or fewer letters.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingAllLetters("cdabeftro");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingAllLettersInvalid() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingAllLetters("abc3f");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingAllLettersEmpty() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a non-empty string.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingAllLetters("");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsUsingAllLettersNonLetter() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter only characters a-z.");
        List<String> actualValue = this.wordGameAssistant.wordsUsingAllLetters("9ahg98");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsMatchingRegularExpressionTypical() throws Exception {
        List<String> expectedValue = Arrays.asList("taal", "tael", "tail", "tall", "teal", "teel",
                "teil", "tell", "till", "tirl", "toil", "toll", "tool", "twal");
        List<String> actualValue = this.wordGameAssistant.wordsMatchingRegularExpression("^t..l$");
        for (int i=0; i < expectedValue.size(); i++) {
            String x = expectedValue.get(i).toUpperCase();
            expectedValue.set(i, x);
        }
        sort(expectedValue);
        sort(actualValue);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordsMatchingRegularExpressionEmpty() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a non-empty string.");
        List<String> actualValue = this.wordGameAssistant.wordsMatchingRegularExpression("");
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordListOrderedBySizeTypical() throws Exception {
        List<String> expectedValue = Arrays.asList("oar", "ora", "raw", "row", "war", "ar", "aw", "or", "ow", "wo");
        List<String> input = Arrays.asList("row", "raw", "war", "wo", "ow", "aw", "ora", "oar", "or", "ar");
        List<String> actualValue = this.wordGameAssistant.wordListOrderedBySize(input);
        for (int i=0; i < expectedValue.size(); i++) {
            String x = expectedValue.get(i).toUpperCase();
            expectedValue.set(i, x);
        }
        sort(expectedValue);
        sort(actualValue);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordListOrderedbySizeEmpty() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a non-empty list.");
        List<String> input = Arrays.asList("");
        List<String> actualValue = this.wordGameAssistant.wordListOrderedBySize(input);
        assertEquals(expectedValue, actualValue);
    }

    @org.junit.Test
    public void wordListOrderedbySizeNonLetter() throws Exception {
        List<String> expectedValue = Arrays.asList("Please enter a list with items containing only letters.");
        List<String> input = Arrays.asList("5qag, [gh, -5, =4, abs, cds");
        List<String> actualValue = this.wordGameAssistant.wordListOrderedBySize(input);
        assertEquals(expectedValue, actualValue);
    }
}