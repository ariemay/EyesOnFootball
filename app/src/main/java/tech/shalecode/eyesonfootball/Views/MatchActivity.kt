package tech.shalecode.eyesonfootball.Views

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import tech.shalecode.eyesonfootball.Adapter.MatchAdapter
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Models.LeaguesItem
import tech.shalecode.eyesonfootball.Presenter.MatchPresenter
import tech.shalecode.eyesonfootball.R
import tech.shalecode.eyesonfootball.Utility.invisible
import tech.shalecode.eyesonfootball.Utility.visible

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
    private lateinit var nameLeague : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        getAPI()

    }

    fun getAPI() {
        var listLeague = ArrayList<LeaguesItem>()

        val queue = Volley.newRequestQueue(this)
        val url: String = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var leagueList = response.toString()
                val jsonObj: JSONObject = JSONObject(leagueList)
                val jsonArray: JSONArray = jsonObj.getJSONArray("leagues")
                val lengthResponse = jsonArray.length()
                val idLeague = arrayOfNulls<String>(lengthResponse)
                val leagueName = arrayOfNulls<String>(lengthResponse)
                for (i in 0 until lengthResponse) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    idLeague[i] = jsonInner.get("idLeague") as String
                    leagueName[i] = jsonInner.get("strLeague") as String
                    listLeague.add(LeaguesItem(idLeague[i].toString(),
                        leagueName[i].toString()))
                }
                spinner.adapter = ArrayAdapter<String>(this@MatchActivity,
                    android.R.layout.simple_spinner_dropdown_item, leagueName)
            },
            Response.ErrorListener { Log.i("ERROR NIH", "league error") })
        queue.add(stringReq)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueID(dataLeagues: List<LeaguesItem>) {
//        swipeRefresh.isRefreshing = false
//        leagues.clear()
//        val message = dataLeagues.getJSO
//        nameLeague.add(dataLeagues.getString("strLeague"))
//        setSpinner(leagues)
    }

    override fun getPrevEvents(dataPrev: List<EventsItem>) {

    }

    override fun showMatch(events: List<EventsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
