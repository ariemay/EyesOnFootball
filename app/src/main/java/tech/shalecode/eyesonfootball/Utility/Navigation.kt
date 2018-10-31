package tech.shalecode.eyesonfootball.Utility

import android.support.design.widget.BottomNavigationView
import android.util.Log
import tech.shalecode.eyesonfootball.R

private val bottomNav by lazy {

    var menuItem: Int

    BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.last_match_nav -> {
                menuItem = 1
                letsSpinner(menuItem!!)
                setDataToContainer(idSpinner.toString(),menuItem!!)
                Log.d("TAG", "ini prev")
                true
            }
            R.id.next_match_nav -> {
                menuItem = 2
                set_spinner(menuItem!!)
                setDataToContainer(idSpinner.toString(),menuItem!!)
                Log.d("TAG", "ini next")
                true
            }else ->{
            true
        }
        }
    }
}

fun letsSpinner(menuItem: Any) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
