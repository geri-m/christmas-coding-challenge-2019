package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String word) {


        for(int indexInPattern = 0; indexInPattern < pattern.length(); indexInPattern++){
            char ofPattern = pattern.charAt(indexInPattern);
            int indexOfCharFound = word.indexOf(ofPattern);
            if (indexOfCharFound >= 0){
                if(indexInPattern +1 < pattern.length())
                    return regex(pattern.substring(indexInPattern+1), word.substring(indexOfCharFound+1));
                else
                    return true;
            } else {
                return false;
            }
        }

        return false;

    }

    @Test
    public void charLen1WorLen1Okay() {
        assertThat(regex("a", "a"), equalTo(true));
    }

    @Test
    public void charLen1WorLen1Fail() {
        assertThat(regex("b", "a"), equalTo(false));
    }

    @Test
    public void charLen1WorLen2Fail() {
        assertThat(regex("ab", "aa"), equalTo(false));
    }


    @Test
    public void charLen2WorLen1Fail() {
        assertThat(regex("ab", "a"), equalTo(false));
    }

}
