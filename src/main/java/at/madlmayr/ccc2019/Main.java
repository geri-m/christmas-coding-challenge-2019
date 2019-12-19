package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String s) {

        for (int indexOfPattern = 0; indexOfPattern < pattern.length(); indexOfPattern++){
            char charOfPattern = pattern.charAt(indexOfPattern);

            if(charOfPattern == '*') {

            } else {
                int indexOfCharOfPattern = s.indexOf(charOfPattern);
                if(indexOfCharOfPattern < 0)
                    return false;
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
        assertThat(regex("*", "a"), equalTo(true));
        assertThat(regex("*", "aa"), equalTo(true));
        assertThat(regex("*", "ab"), equalTo(true));
        assertThat(regex("*", "ba"), equalTo(true));
        assertThat(regex("*", "b"), equalTo(true));
        assertThat(regex("*", "bb"), equalTo(true));
    }


}
