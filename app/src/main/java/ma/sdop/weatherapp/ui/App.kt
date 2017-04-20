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

// usage of custom delegate
// var example: Int by DelegatesExt.notNullSingleValue()

// example of custom delegate.
class NotNullSingleValueVar<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null ) value
        else throw IllegalStateException("${property.name} alread initialized." )
    }
}

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}