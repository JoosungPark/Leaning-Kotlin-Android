package ma.sdop.weatherapp.domain.datasource

import ma.sdop.weatherapp.data.database.ForecastDb
import ma.sdop.weatherapp.data.server.ForecastServer
import ma.sdop.weatherapp.domain.model.Forecast
import ma.sdop.weatherapp.domain.model.ForecastList
import ma.sdop.weatherapp.extensions.firstResult

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if ( res != null && res.size >= days ) res else null
    }

    fun requestDayForecast(id: Long) : Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T: Any> requestToSources(f: (ForecastDataSource) -> T?): T = SOURCES.firstResult { f(it) }
}