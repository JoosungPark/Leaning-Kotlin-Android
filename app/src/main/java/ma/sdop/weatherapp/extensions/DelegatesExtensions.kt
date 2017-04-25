package ma.sdop.weatherapp.extensions

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
        this.value = if (this.value == null ) value
        else throw IllegalStateException("${property.name} alread initialized." )
    }
}

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}