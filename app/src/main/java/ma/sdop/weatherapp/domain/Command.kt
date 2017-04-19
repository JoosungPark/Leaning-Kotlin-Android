package ma.sdop.weatherapp.domain

/**
 * Created by parkjoosung on 2017. 4. 19..
 */
interface Command<T> {
    fun execute(): T
}