package ma.sdop.weatherapp.data.server

import ma.sdop.weatherapp.domain.datasource.ForecastDataSource
import ma.sdop.weatherapp.data.database.ForecastDb
import ma.sdop.weatherapp.domain.model.Forecast
import ma.sdop.weatherapp.domain.model.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(), val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()
}