package at.madlmayr.ccc2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class Main {

    public boolean regex(String pattern, String s) {
        return false;
    }

    @Test
    public void singleChar() {
        assertThat(regex("a", "a"), equalTo(true));
    }


}
