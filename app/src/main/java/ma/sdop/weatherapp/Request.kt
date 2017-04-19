package ma.sdop.weatherapp

import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import org.jetbrains.anko.longToast

/**
 * Created by parkjoosung on 2017. 4. 18..
 */
class Request(val url: String) {
    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }



}