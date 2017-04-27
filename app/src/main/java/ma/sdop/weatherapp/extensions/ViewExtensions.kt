package ma.sdop.weatherapp.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by parkjoosung on 2017. 4. 19..
 */

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)