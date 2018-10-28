package tech.shalecode.eyesonfootball.Views

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Spinner
import tech.shalecode.eyesonfootball.Adapter.MatchAdapter
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Models.LeaguesItem
import tech.shalecode.eyesonfootball.Presenter.MatchPresenter
import tech.shalecode.eyesonfootball.R

class MatchActivity : AppCompatActivity(), MainView {

    private var events: MutableList<EventsItem> = mutableListOf()
    private var leagues : MutableList<LeaguesItem> = mutableListOf()
    private lateinit var presenter : MatchPresenter
    private lateinit var adapter : MatchAdapter
    private lateinit var listMatches : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var spinner : Spinner
    private lateinit var idLeague : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val spinnerItems = resources.getStringArray(leagues)



    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMatch(events: List<EventsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
