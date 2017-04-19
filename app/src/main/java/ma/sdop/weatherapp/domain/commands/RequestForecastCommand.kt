package ma.sdop.weatherapp.domain.commands

import ma.sdop.weatherapp.data.ForecastRequest
import ma.sdop.weatherapp.domain.mappers.ForecastDataMapper
import ma.sdop.weatherapp.domain.model.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 19..
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}