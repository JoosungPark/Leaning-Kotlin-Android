package ma.sdop.weatherapp.domain.mappers

import ma.sdop.weatherapp.data.Forecast
import ma.sdop.weatherapp.data.ForecastResult
import ma.sdop.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
// As we are using two classes with the same name we can give a specific name to one of them so that we don’t need to write the complete package:
import ma.sdop.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastLstToDomain(forecast.list))
    }

    private fun convertForecastLstToDomain(list: List<Forecast>): List<ma.sdop.weatherapp.domain.model.Forecast> {
        // We can loop over the collection that easily and return a new list with the converted items.
        // Kotlin provides a good set of functional operations over list, which apply an operation for all the items in a list and transform them in any way.
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ma.sdop.weatherapp.domain.model.Forecast {
        return ma.sdop.weatherapp.domain.model.Forecast(convertDate(forecast.dt), forecast.description, forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}