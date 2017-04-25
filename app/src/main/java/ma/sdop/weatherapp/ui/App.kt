package ma.sdop.weatherapp.ui

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by parkjoosung on 2017. 4. 20..
 */
class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}

