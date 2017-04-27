package ma.sdop.weatherapp.ui.activities

import android.app.Service
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ma.sdop.weatherapp.R
import ma.sdop.weatherapp.domain.commands.RequestForecastCommand
import ma.sdop.weatherapp.extensions.textColor
import ma.sdop.weatherapp.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolBar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                // lambda usage
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    } }
