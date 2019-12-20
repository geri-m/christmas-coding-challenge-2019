package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String word) {

        if (pattern.length() == 0 && word.length() != 0)
            return false;
        else if (pattern.length() != 0 && word.length() == 0)
            // if the word is already empty, but look-ahead shows a star '*' run another recursion.
            if (pattern.length() > 1 && pattern.charAt(1) == '*') {
                return regex(pattern.substring(2), word);
            } else {
                return false;
            }
        else if (pattern.length() == 0 && word.length() == 0)
            return true;
        else {
            // do a look ahead.
            if (pattern.length() > 1 && pattern.charAt(1) == '*') {
                // if the current pattern char, matches the word, perfect
                // go on, but don't change the pattern.
                if (pattern.charAt(0) == word.charAt(0) || pattern.charAt(0) == '.') {
                    return regex(pattern, word.substring(1));
                } else {
                    return regex(pattern.substring(2), word);
                }
            } else {
                // if there is a match with the current char, run the algorithm again
                if (pattern.charAt(0) == word.charAt(0) || pattern.charAt(0) == '.') {
                    return regex(pattern.substring(1), word.substring(1));
                } else {
                    // if there is no match at the current char, fail.
                    return false;
                }
            }
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
