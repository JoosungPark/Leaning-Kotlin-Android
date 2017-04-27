package ma.sdop.weatherapp.domain.commands

import ma.sdop.weatherapp.domain.datasource.ForecastProvider
import ma.sdop.weatherapp.domain.model.Forecast

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
class RequestDayForecastCommand( val id: Long, val forecastProvider: ForecastProvider = ForecastProvider()): Command<Forecast> {
    override fun execute(): Forecast = forecastProvider.requestDayForecast(id)
}
