package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String s) {

        for (int indexOfPattern = 0; indexOfPattern < pattern.length(); indexOfPattern++){
            char charOfPattern = pattern.charAt(indexOfPattern);


            if(indexOfPattern < pattern.length() -1) {
                char lookAheadChar =  pattern.charAt(indexOfPattern + 1);
                // a Star "nulls" the result of the previous character and
                // therefore this pattern is _always_ found and true.
                if(lookAheadChar == '*'){
                    return true;
                }
            }
            else {
                // this actually covers only the last char of the pattern or the pattern with a single char.
                if(charOfPattern == '*') {
                   return false;
                } else {
                    int indexOfCharOfPattern = s.indexOf(charOfPattern);
                    if(indexOfCharOfPattern < 0)
                        return false;
                }
            }
        }

        return true;
    }

    @Test
    public void singleChar() {
        assertThat(regex("a", "a"), equalTo(true));
        assertThat(regex("a", "aa"), equalTo(true));
        assertThat(regex("a", "ab"), equalTo(true));
        assertThat(regex("a", "ba"), equalTo(true));
        assertThat(regex("a", "b"), equalTo(false));
        assertThat(regex("a", "bb"), equalTo(false));
    }

    @Test
    public void singleStar(){
        assertThat(regex("*", "a"), equalTo(false));
        assertThat(regex("*", "aa"), equalTo(false));
        assertThat(regex("*", "ab"), equalTo(false));
        assertThat(regex("*", "ba"), equalTo(false));
        assertThat(regex("*", "b"), equalTo(false));
        assertThat(regex("*", "bb"), equalTo(false));
    }

    @Test
    public void singleCharAndStar(){
        assertThat(regex("a*", "a"), equalTo(true));
        assertThat(regex("a*", "aa"), equalTo(true));
        assertThat(regex("a*", "ab"), equalTo(true));
        assertThat(regex("a*", "ba"), equalTo(true));
        assertThat(regex("a*", "b"), equalTo(true));
        assertThat(regex("a*", "bb"), equalTo(true));
    }

}
