package ma.sdop.weatherapp.ui

import android.app.Application
import ma.sdop.weatherapp.extensions.DelegatesExt

/**
 * Created by parkjoosung on 2017. 4. 20..
 */
class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
//        lateinit var instance: App
//            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}

