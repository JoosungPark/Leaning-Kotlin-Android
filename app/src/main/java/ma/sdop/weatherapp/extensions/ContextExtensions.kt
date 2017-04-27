package ma.sdop.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)