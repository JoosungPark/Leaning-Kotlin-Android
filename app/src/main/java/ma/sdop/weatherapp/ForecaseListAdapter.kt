package ma.sdop.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import ma.sdop.weatherapp.domain.ForecastList

/**
 * Created by parkjoosung on 2017. 4. 18..
 */
class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    // with is a useful function included in the standard Kotlin library.
    // It basically receives an object and an extension function as parameters,
    // and makes the object execute the function.

    // apply methods
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }
    }
    override fun getItemCount(): Int = weekForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)




}
