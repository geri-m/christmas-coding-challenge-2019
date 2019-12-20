package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String word) {

        // Case: If pattern is already empty, but we still have chars, this must fail.
        if (pattern.length() == 0 && word.length() > 0)
            return false;
        // Case: if we have a pattern but word is '', check if we are in '*' section and decide.
        else if (pattern.length() > 0 && word.length() == 0)
            if (isNextCharAStar(pattern)) {
                return skipStarSegmentofPattern(pattern, word);
            } else {
                return false;
            }
        // Case: if pattern and word are both '', then this okay
        else if (pattern.length() == 0 && word.length() == 0)
            return true;
        else {
            // do a look ahead.
            if (isNextCharAStar(pattern)) {
                // if the current pattern char, matches the word, perfect
                // go on, but don't change the pattern.
                if (compareFirstChars(pattern, word)) {
                    return regex(pattern, word.substring(1));
                } else {
                    return skipStarSegmentofPattern(pattern, word);
                }
            } else {
                // if there is a match with the current char, run the algorithm again
                if (compareFirstChars(pattern, word)) {
                    return regex(pattern.substring(1), word.substring(1));
                } else {
                    // if there is no match at the current char, fail.
                    return false;
                }
            }
        }
    }

    private boolean compareFirstChars(String pattern, String word){
        return pattern.charAt(0) == word.charAt(0) || pattern.charAt(0) == '.';
    }

    private boolean isNextCharAStar(String pattern){
        return pattern.length() > 1 && pattern.charAt(1) == '*';
    }

    private boolean skipStarSegmentofPattern(String pattern, String word){
        return regex(pattern.substring(2), word);
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
