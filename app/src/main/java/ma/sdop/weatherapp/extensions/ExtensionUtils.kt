package ma.sdop.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by parkjoosung on 2017. 4. 27..
 */

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}