package ma.sdop.weatherapp.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by parkjoosung on 2017. 4. 27..
 */

public class Ex {
    private void ex() {

    }

    void copyStrings(Collection<? super String> to, Collection<String> from) {
        to.addAll(from);
    }
}
