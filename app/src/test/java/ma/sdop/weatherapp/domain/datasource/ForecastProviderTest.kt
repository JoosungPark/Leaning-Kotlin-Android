package ma.sdop.weatherapp.domain.datasource

import ma.sdop.weatherapp.domain.model.Forecast
import ma.sdop.weatherapp.domain.model.ForecastList
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by parkjoosung on 2017. 5. 2..
 */
class ForecastProviderTest {
    // We need backquotes for when function. This is because when is a reserved work in Kotlin, so we need to escape it if we find some Java code that uses it.
    @Test fun testDataSourceReturnsValue() {
        val ds = Mockito.mock(ForecastDataSource::class.java)
        Mockito.`when`(ds.requestDayForecast(0)).then { Forecast(0, 0, "desc", 20, 0, "url") }

        val provider = ForecastProvider(listOf(ds))
        Assert.assertNotNull(provider.requestDayForecast(0))
    }

    @Test fun emptyDatabaseReturnsServerValue() {
        val db = Mockito.mock(ForecastDataSource::class.java)
        val server = Mockito.mock(ForecastDataSource::class.java)
        Mockito.`when`(server.requestForecastByZipCode(Mockito.any(Long::class.java), Mockito.any(Long::class.java)))
                .then { ForecastList(0, "city", "country", listOf()) }

        val provider = ForecastProvider(listOf(db, server))
        Assert.assertNotNull(provider.requestByZipCode(0, 0))
    }

}