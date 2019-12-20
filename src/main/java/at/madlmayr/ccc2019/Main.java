package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {


    public static void main(final String[] args){
        if(args == null || args.length != 2) {
            System.out.println("Usage: java -jar ccc-2019 <pattern> <word>");
            return;
        }

        if(regex(args[0], args[1]))
            System.out.println(String.format("Word '%s' is matched by pattern '%s'", args[0], args[1]));
        else
            System.out.println(String.format("Word '%s' is NOT matched by pattern '%s'", args[0], args[1]));
    }


    public static boolean regex(String pattern, String word) {
        // Case: If pattern is already empty, but we still have chars, this must fail.
        if (pattern.length() == 0 && word.length() > 0)
            return false;
        // Case: if we have a pattern but word is '', check if we are in '*' section and decide.
        else if (pattern.length() > 0 && word.length() == 0)
            return handleNextCharIsAStar(pattern, word);
        // Case: if pattern and word are both '', then this okay
        else if (pattern.length() == 0 && word.length() == 0)
            return true;
        // Case: all cases, where we have characters in pattern and word
        else {
            if (pattern.charAt(0) == word.charAt(0) || pattern.charAt(0) == '.') {
                if (isNextCharAStar(pattern)) {
                    return regex(pattern, word.substring(1));
                } else {
                    return regex(pattern.substring(1), word.substring(1));
                }
            } else {
                return handleNextCharIsAStar(pattern, word);
            }
        }
    }

    private static boolean isNextCharAStar(String pattern){
        return pattern.length() > 1 && pattern.charAt(1) == '*';
    }

    private static boolean handleNextCharIsAStar(String pattern, String word){
        if (isNextCharAStar(pattern)) {
            return regex(pattern.substring(2), word);
        } else {
            return false;
        }
    }

    @Test
    public void patternLen0WordLen1Fail() {
        assertThat(regex("", "a"), equalTo(false));
    }

    @Test
    public void patternLen1WordLen0Fail() {
        assertThat(regex("a", ""), equalTo(false));
    }

    @Test
    public void patternLen0WordLen0Okay() {
        assertThat(regex("", ""), equalTo(true));
    }

    @Test
    public void patternLen1WordLen1Okay() {
        assertThat(regex("a", "a"), equalTo(true));
    }

    @Test
    public void patternLen2WordLen1Okay() {
        assertThat(regex("aa", "a"), equalTo(false));
    }

    @Test
    public void dotLen1WordLen1Okay() {
        assertThat(regex(".", "a"), equalTo(true));
    }

    @Test
    public void starLen1WordLen1Okay() {
        assertThat(regex("a*", "a"), equalTo(true));
    }


    @Test
    public void ginosReferenceTests() {
        assertThat(regex("ab", "aba"), equalTo(false));
        assertThat(regex("a*", "aa"), equalTo(true));
        assertThat(regex(".*", "ab"), equalTo(true));
        assertThat(regex(".", "ab"), equalTo(false));
        assertThat(regex("c*a*b", "aab"), equalTo(true));
        assertThat(regex("a*", "aaa"), equalTo(true));
        assertThat(regex("a*b", "aac"), equalTo(false));
    }

}
