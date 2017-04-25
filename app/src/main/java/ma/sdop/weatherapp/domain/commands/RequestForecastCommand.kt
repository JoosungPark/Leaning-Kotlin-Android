package ma.sdop.weatherapp.domain.commands

import ma.sdop.weatherapp.data.server.ForecastRequest
import ma.sdop.weatherapp.domain.mappers.ForecastDataMapper
import ma.sdop.weatherapp.domain.model.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 19..
 */
class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}