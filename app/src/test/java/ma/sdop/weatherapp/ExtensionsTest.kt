package ma.sdop.weatherapp

import ma.sdop.weatherapp.extensions.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

/**
 * Created by parkjoosung on 2017. 5. 2..
 */
class ExtensionsTest {
    @Test fun testLongToDateString() {
        assertEquals("2015. 10. 20", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("2015년 10월 20일 화요일", 1445275635000L.toDateString(DateFormat.FULL))
    }
}