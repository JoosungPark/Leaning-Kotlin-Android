package ma.sdop.weatherapp.domain.datasource

import ma.sdop.weatherapp.domain.model.Forecast
import ma.sdop.weatherapp.domain.model.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?

}