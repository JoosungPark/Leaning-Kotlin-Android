package ma.sdop.weatherapp.domain.mappers

import ma.sdop.weatherapp.data.server.Forecast
import ma.sdop.weatherapp.data.server.ForecastResult
import ma.sdop.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
// As we are using two classes with the same name we can give a specific name to one of them so that we don’t need to write the complete package:
import ma.sdop.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastLstToDomain(list))
    }

    private fun convertForecastLstToDomain(list: List<Forecast>): List<ModelForecast> {
        // We can loop over the collection that easily and return a new list with the converted items.
        // Kotlin provides a good set of functional operations over list, which apply an operation for all the items in a list and transform them in any way.
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(dt, description, temp.max.toInt(), temp.min.toInt(), generateIconUrl(icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}