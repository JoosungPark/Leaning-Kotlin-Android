package ma.sdop.weatherapp.data.server

import ma.sdop.weatherapp.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import ma.sdop.weatherapp.domain.model.Forecast as ModelForecast

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
class ServerDataMapper {
    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, description, temp.max.toInt(), temp.min.toInt(), generateIconUrl(icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}