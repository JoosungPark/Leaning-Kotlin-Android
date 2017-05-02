package ma.sdop.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import ma.sdop.weatherapp.R
import ma.sdop.weatherapp.extensions.ctx
import ma.sdop.weatherapp.extensions.slideEnter
import ma.sdop.weatherapp.extensions.slideExit
import ma.sdop.weatherapp.ui.App
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by parkjoosung on 2017. 4. 27..
 */
interface ToolbarManager {
    val toolBar: Toolbar

    var toolbarTitle: String
        get() = toolBar.title.toString()
        set(value) { toolBar.title = value }

    fun initToolbar() {
        toolBar.inflateMenu(R.menu.menu_main)
        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolBar.ctx.startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolBar.navigationIcon = createUpDrawable()
        toolBar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolBar.ctx).apply { progress = 1f }

    // toolbar need to be attached to a scroll, and animates the toolbar depending on the direction of the scroll. The toolbar will be hidden while we are scrolling down and displayed again when scrolling up.
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if ( dy > 0 ) toolBar.slideExit() else toolBar.slideEnter()
            }
        })
    }
}