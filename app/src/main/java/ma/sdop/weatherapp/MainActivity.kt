package ma.sdop.weatherapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ma.sdop.weatherapp.domain.RequestForecastCommand
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

val ViewGroup.childViews: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Hello World!")
        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result)
            }
        }
    }

}

