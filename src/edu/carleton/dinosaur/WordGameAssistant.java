/**
 * Veronica Child, Adam Tigar, and Liv Phillips
 *
 * WordGameAssistant
 * Public interfaces documented by Jeff Ondich, 30 March 2016.
 *
 * The main support class for the WordGameAssistant project in
 * CS 257, Spring 2016, Carleton College. An object of this class
 * will encapsulate a dictionary (i.e. a list of strings considered
 * to be legal words for the word game in question), and provides
 * methods for accessing the dictionary in a variety of ways.
 */

package edu.carleton.dinosaur;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordGameAssistant {
    private final ArrayList<String> dictionary;
    private StringBuilder combination_output = new StringBuilder();
    private ArrayList<String> OnlyLettersUnchecked;
    private ArrayList<String> OnlyLettersNoRepeatsUnchecked;
    /**
     * Initializes this WordGameAssistant given a word list contained in
     * the specified file. The file must consist of one word per line.
     *
     * @param dictionaryFilePath the path to the dictionary file
     */
    public WordGameAssistant(String dictionaryFilePath) {
        String curLine;
        ArrayList<String> localdict = new ArrayList<>();
        this.dictionary = localdict;
        try{
            BufferedReader br = new BufferedReader(new FileReader(dictionaryFilePath));
            while((curLine = br.readLine()) != null) {
                localdict.add(curLine);
            }
        }
        catch(Exception bad) {
            bad.printStackTrace();
        }
    }

    /**
     * Initializes this WordGameAssistant given the specified list of words.
     *
     * @param wordList the words
     */
    public WordGameAssistant(String[] wordList) {
        this.dictionary = new ArrayList<String>();
        for (String s : wordList) {
            this.dictionary.add(s);
        }
    }

    /**
     * Helper method to check that string contains legal characters.
     * @param letters
     * @return boolean saying whether it has all legal characters.
     */
    private static boolean checkForChars(String letters) {
        char[] letterchars = letters.toCharArray();
        for (char c : letterchars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check that string is not empty
     * @param letters
     * @return boolean stating if it is a non-empty string.
     */
    private static boolean checkForEmpty(String letters) {
        return !letters.equals("");
    }

    /**
     * Helper method to check that string is within size parameters.
     * @param letters
     * @return boolean stating if it is within size parameters.
     */
    private static boolean checkForLength(String letters) {
        return letters.length() <= 8;
    }

    /**
     * Helper method that performs all legality checks.
     * @param letters
     * @return List<String> containing either an error message, or a message saying to keep going.
     */
    private static List<String> legalValueCheck(String letters) {
        boolean empty = checkForEmpty(letters);
        boolean chara = checkForChars(letters);
        boolean eight = checkForLength(letters);
        List<String> allLegal = Arrays.asList("Move on.");

        if (!empty) {
            List<String> emptyString = Arrays.asList("Please enter a non-empty string.");
            return emptyString;
        }
        if (!chara) {
            List<String> notChars = Arrays.asList("Please enter only characters a-z.");
            return notChars;
        }

        if (!eight) {
            List<String> tooLong = Arrays.asList("Please enter eight or fewer letters.");
            return tooLong;
        }
        return allLegal;
    }

    /**
     * Helper method to check that strings in list contains legal characters.
     * @param letters
     * @return boolean stating whether all strings have only legal characters.
     */
    private static boolean checkForCharsList(List<String> letters) {
        for (int i = 0; i < letters.size(); i++) {
            char[] letterchars = letters.get(i).toCharArray();
            for (char c : letterchars) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method to check that list is not empty.
     * @param letters
     * @return boolean stating if it is a non-empty list.
     */
    private static boolean checkForEmptyList(List<String> letters) {
        List<String> expectedValue = Arrays.asList("");
        return !letters.equals(expectedValue);
    }

    /**
     * Helper method to perform all legality checks on Lists.
     * @param letters
     * @return List<String> containing either an error message, or a message saying to keep going.
     */
    private static List<String> legalValueCheckList(List letters) {
        boolean empty = checkForEmptyList(letters);
        boolean chara = checkForCharsList(letters);
        List<String> allLegal = Arrays.asList("Move on.");

        if (!empty) {
            List<String> emptyString = Arrays.asList("Please enter a non-empty list.");
            return emptyString;
        }
        if (!chara) {
            List<String> notChars = Arrays.asList("Please enter a list with items containing only letters.");
            return notChars;
        }
        return allLegal;
    }

    /**
     * Dictionary lookup for individual words.
     * @param word
     * @return boolean stating whether word is in dictionary.
     */
    private boolean dictLookupReturnBool(String word){
        boolean val = false;
        for (String string : this.dictionary) {
            if(string.matches(word)){
                val = true;
            }
        }
        return val;
    }

    /**
     * Dictionary lookup for regex words.
     * @param regex
     * @return List<String> of all legal words in dictionary that match the regex.
     */
    private List<String> dictLookUpReturnList(String regex) {
        ArrayList<String> matchingStrings = new ArrayList<>();
        for (String haystack: this.dictionary) {
            if (haystack.matches(regex)) {
                matchingStrings.add(haystack);
            }
        }
        return matchingStrings;
    }

    /**
     * Finds all permutations of a given string of letters.
     * @param letters
     * @return List<String> of all permutations of the string.
     */
    private static List<String> findPermutations(String letters){
        List<String> allPerms = new ArrayList<>();

        char[] charas = letters.toCharArray();
        int len = charas.length;
        Arrays.sort(charas);
        allPerms.add(new String(charas));

        while (true) {
            int i = len-2;
            while (i >= 0 && charas[i] >= charas[i+1]) {
                i--;
            }
            if (i < 0) {
                return allPerms;
            }
            else {
                int j = len-1;
                while(j > i && charas[j] <= charas[i]) {
                    j--;
                }
                if (j < charas.length) {
                    char temp = charas[j];
                    charas[j] = charas[i];
                    charas[i] = temp;

                    for (int k = i+1; k < (i+1+(len-i-1)/2); k++) {
                        temp = charas[len-k+i];
                        charas[len-k+i] = charas[k];
                        charas[k] =  temp;
                    }
                    allPerms.add(new String(charas));
                }
            }
        }
    }


    /**
     * This method takes a string of available letters, and generates a list of
     * all the legal words that can be formed from only the specified letters, without
     * repetition. For example, if we're using a normal English legal word list
     * and letters="woew", the resulting list will contain "wow", "woe", "owe",
     * and "we" (and maybe "wo" and "ew" if we're using a Scrabble-like dictionary
     * containing those words).
     *
     * Matches are case-insensitive. The list of words generated is not guaranteed
     * to be in any particular order.
     *
     * @param letters the list of available letters
     * @return the list of matching words
     */

    public List<String> wordsUsingOnlyLetters(String letters) {
        List<String> legalValues = legalValueCheck(letters);
        List<String> moveOn = Arrays.asList("Move on.");
        if (!legalValues.equals(moveOn)) {
            return legalValues;
        }

        ArrayList<String> matchingStrings = new ArrayList<>();

        ArrayList<Character> letterschar = new ArrayList<>();
        for (char c : letters.toCharArray()) {
            letterschar.add(c);
        }
        ArrayList<Character> temporary_characters = new ArrayList<>();

        for (String word : this.dictionary) {
            temporary_characters = (ArrayList<Character>) letterschar.clone();
            boolean word_exists = true;
            for (int j = 0; j < word.length(); j++) {
                if (!temporary_characters.contains(word.charAt(j))) {
                    word_exists = false;
                }
            }
            if (word_exists == true) {
                matchingStrings.add(word);
            }
        }

        return matchingStrings;


    }


    /**
     * This method takes a string of available letters, and generates a list of
     * all the legal words that can be formed from only the specified letters, but allowing
     * letter repetition. For example, if we're using a normal English legal word list
     * and letters="oew", the resulting list will contain "wow", "woe", "owe", "wee",
     * "ewe", "woo", and "we" (and maybe "wo" and "ew" if we're using a Scrabble-like
     * dictionary containing those words).
     *
     * Matches are case-insensitive. The list of words generated is not guaranteed
     * to be in any particular order.
     *
     * @param letters the list of available letters
     * @return the list of matching words
     */
    public List<String> wordsUsingLettersAllowingRepetition(String letters) {
        List<String> legalValues = legalValueCheck(letters);
        List<String> moveOn = Arrays.asList("Move on.");
        if (!legalValues.equals(moveOn)) {
            return legalValues;
        }

        ArrayList<String> matchingStrings = new ArrayList<>();
        ArrayList<Character> letterschar = new ArrayList<>();
        for (char c : letters.toCharArray()) {
            letterschar.add(c);
        }
        ArrayList<Character> temporary_characters = new ArrayList<>();

        for (String word : dictionary) {
            temporary_characters = (ArrayList<Character>) letterschar.clone();
            boolean word_exists = true;
            for (int j = 0; j < word.length(); j++) {
                if (temporary_characters.contains(word.charAt(j))) {
                    temporary_characters.remove(temporary_characters.indexOf(word.charAt(j)));
                } else {
                    word_exists = false;
                }
            }
            if (word_exists == true) {
                matchingStrings.add(word);
            }
        }

        return matchingStrings;
    }

    /**
     * This method takes a string of available letters, and generates a list of
     * all the legal words that can be formed using exactly the specified letters, without
     * repetition or omission. For example, if we're using a normal English legal word list
     * and letters="ecra", the resulting list will contain "race", "care", and "acre".
     *
     * Matches are case-insensitive. The list of words generated is not guaranteed
     * to be in any particular order.
     *
     * @param letters the list of available letters
     * @return the list of matching words
     */
    public List<String> wordsUsingAllLetters(String letters) {
        List<String> legalValues = legalValueCheck(letters);
        List<String> moveOn = Arrays.asList("Move on.");
        boolean goBack = false;
        if (!legalValues.equals(moveOn)) {
            return legalValues;
        }
        letters = letters.toUpperCase();
        List<String> allResults = findPermutations(letters);
        for (int j=0; j < allResults.size() + 1; j++){
            if (goBack) {
                j = j-1;
                goBack = false;
            }
            if (!dictLookupReturnBool(allResults.get(j))) {
                allResults.remove(j);
                goBack = true;
            }
        }
        return allResults;
    }

    /**
     * Returns the list of all legal words matching the specified regular expression.
     * For example, if we're using a normal English legal word list and regex="^t.a.l$",
     * the resulting list will contain "trail" and "trawl".
     *
     * Matches are case-insensitive. The list of words generated is not guaranteed
     * to be in any particular order.
     *
     * @param regex the regular expression
     * @return the list of matching words
     */
    public List<String> wordsMatchingRegularExpression(String regex) {
        List<String> legalValues = legalValueCheck(regex);
        List<String> moveOn = Arrays.asList("Move on.");
        List<String> moveOn2 = Arrays.asList("Please enter only characters a-z.");
        if (!legalValues.equals(moveOn) && !legalValues.equals(moveOn2)) {
            return legalValues;
        }
        regex = regex.toUpperCase();
        List<String> matchingStrings = dictLookUpReturnList(regex);
        return matchingStrings;
    }

    /**
     * Returns a copy of the specified list, in decreasing order of word length.
     * Words of identical length will appear in alphabetical order (or, more accurately,
     * in increasing order based on the String.compare method).
     *
     * @param wordList the original list
     * @return the sorted list
     */
    public List<String> wordListOrderedBySize(List<String> wordList) {
        List<String> legalList = legalValueCheckList(wordList);
        List<String> moveOn = Arrays.asList("Move on.");
        if (!legalList.equals(moveOn)){
            return legalList;
        }
        for (int i=0; i < wordList.size(); i++) {
            String x = wordList.get(i).toUpperCase();
            wordList.set(i, x);
        }
        List<String> sortedWordList = new LinkedList<>();

        for (int i = 0; i < wordList.size(); i++) {
            String current = wordList.get(i);

            if (sortedWordList.size() == 0) {
                sortedWordList.add(current);
            } else {
                for (int j = 0; j < sortedWordList.size(); j++) {
                    String temp = sortedWordList.get(j);

                    if (current.length() > temp.length()) {
                        sortedWordList.add(j, current);
                        break;

                    } else if (current.length() == temp.length()) {
                        if (current.compareTo(temp) < 0) {
                            sortedWordList.add(j, current);
                            break;
                        } else if (j == sortedWordList.size() - 1) {
                            sortedWordList.add(current);
                            break;
                        } else {
                            continue;
                        }

                    } else if (j == sortedWordList.size() - 1) {
                        sortedWordList.add(current);
                        break;
                    }
                }
            }
        }
        return sortedWordList;
    }

    public static void main(String args[])
    {
        // Parse the command line
        String letters = null;
        String action = null;
        String dictionary = null;
        boolean hasBadArguments = false;
        for (String arg: args) {
            if (arg.startsWith("--dictionary=")) {
                dictionary = arg.substring("--dictionary".length() + 1);
            } else if (arg.equals("words")) {
                action = "words";
            } else if (args.equals("use-all-letters")) {
                action = "use-all-letters";
            } else if (args.equals("allow-repetition")) {
                action = "allow-repetition";
            }
            else if (action != null) {
                letters = arg;
            } else {
                hasBadArguments = true;
                break;
            }
        }

        // If the parsing went badly, print a usage statement and exit.
        if (hasBadArguments || action == null || letters == null) {
            System.err.println("Usage: java WordGameAssistant [--dictionary=path-to-dictionary-file] action letters");
            System.err.println("  Prints all words given the letters and specified action in the dictionary.");
            System.err.println("  If the --dictionary option is present, matches are sought in the file named by");
            System.err.println("  the user. Otherwise, matches are sought in a general dictionary. If both --path and");
            System.err.println("  --haystack options are present, only the haystack will be searched.");
            System.exit(1);
        }

        // Find all matches in the haystack
        List<String> sortedMatches = null;
        List<String> allMatches = null;
        if (dictionary != null) {
            WordGameAssistant assistant = new WordGameAssistant(dictionary);
            if (action == "words") {
                allMatches = assistant.wordsUsingOnlyLetters(letters);
            } else if (action == "use-all-letters") {
                allMatches = assistant.wordsUsingAllLetters(letters);
            } else if (action == "allowing-repetition") {
                allMatches = assistant.wordsUsingLettersAllowingRepetition(letters);
            }
            sortedMatches = assistant.wordListOrderedBySize(allMatches);

        } else {
            WordGameAssistant assistant = new WordGameAssistant("dictionary.txt");
            if (action == "words") {
                allMatches = assistant.wordsUsingOnlyLetters(letters);
            } else if (action == "use-all-letters") {
                allMatches = assistant.wordsUsingAllLetters(letters);
            } else if (action == "allowing-repetition") {
                allMatches = assistant.wordsUsingLettersAllowingRepetition(letters);
            }
            sortedMatches = assistant.wordListOrderedBySize(allMatches);
        }

        // Print the matches
        for (String match: sortedMatches) {
            System.out.println(match);
        }
    }
}
