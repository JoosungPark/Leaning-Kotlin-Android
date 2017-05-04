package ma.sdop.weatherapp.extensions

import android.content.Context
import java.lang.IllegalStateException
import kotlin.reflect.KProperty

/**
 * Created by parkjoosung on 2017. 4. 25..
 */
// usage of custom delegate
// var example: Int by DelegatesExt.notNullSingleValue()

// example of custom delegate.
class NotNullSingleValueVar<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }

}

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()

//    fun longPreference(context: Context, name: String, default: Long) = LongPreference(context, name, default)

    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}

//class LongPreference(val context: Context, val name: String, val default: Long) {
//    val prefs by lazy {
//        context.getSharedPreferences("default", Context.MODE_PRIVATE)
//    }
//
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long = prefs.getLong(name, default)
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = prefs.edit().putLong(name, value).apply()
//}

class Preference<T>(val context: Context, val name: String, val default: T) {
    val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = findPreference(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(name, value)

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when(default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }

        return res as T
    }

    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when(value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}