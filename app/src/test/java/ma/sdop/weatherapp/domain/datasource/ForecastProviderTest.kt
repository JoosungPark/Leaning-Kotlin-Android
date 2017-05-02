package ma.sdop.weatherapp.domain.datasource

import ma.sdop.weatherapp.domain.model.Forecast
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by parkjoosung on 2017. 5. 2..
 */
class ForecastProviderTest {
    // We need backquotes for when function. This is because when is a reserved work in Kotlin, so we need to escape it if we find some Java code that uses it.
    @Test fun testDataSourceReturnsValue() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then { Forecast(0, 0, "desc", 20, 0, "url") }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestDayForecast(0))
    }

}