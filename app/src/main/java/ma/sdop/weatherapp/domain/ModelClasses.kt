package ma.sdop.weatherapp.domain

/**
 * Created by parkjoosung on 2017. 4. 19..
 */
// Example
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int): Forecast = dailyForecast[position]

}


data class Forecast(val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)