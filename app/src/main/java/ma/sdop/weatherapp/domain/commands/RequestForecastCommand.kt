package ma.sdop.weatherapp.domain.commands

import ma.sdop.weatherapp.domain.datasource.ForecastProvider
import ma.sdop.weatherapp.domain.model.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 19..
 */
class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}