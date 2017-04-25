package ma.sdop.weatherapp.extensions

/**
 * Created by parkjoosung on 2017. 4. 25..
 */
fun <K, V: Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>>
        = map({ Pair(it.key, it.value!!)}).toTypedArray()