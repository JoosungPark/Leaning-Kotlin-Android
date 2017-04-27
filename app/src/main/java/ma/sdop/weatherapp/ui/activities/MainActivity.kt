package ma.sdop.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ma.sdop.weatherapp.R
import ma.sdop.weatherapp.domain.commands.RequestForecastCommand
import ma.sdop.weatherapp.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                // lambda usage
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                title = "${result.city} (${result.country})"
            }
        }
    }


}
