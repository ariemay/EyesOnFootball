package tech.shalecode.eyesonfootball.Views.Matchs

/*

Originally Made by Arie May Wibowo
For Dicoding Submission 3
Thank you.

shalecode.tech => My personal domain, not released yet. Still trying to use Django for it.

 */

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_match.*
import org.json.JSONArray
import org.json.JSONObject
import tech.shalecode.eyesonfootball.Adapter.MatchAdapter
import tech.shalecode.eyesonfootball.Fragment.FavoriteMatchFragment
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Models.LeaguesItem
import tech.shalecode.eyesonfootball.Presenter.MatchPresenter
import tech.shalecode.eyesonfootball.R
import tech.shalecode.eyesonfootball.Utility.OutputServerStats
import tech.shalecode.eyesonfootball.Utility.invisible
import tech.shalecode.eyesonfootball.Utility.visible
import tech.shalecode.eyesonfootball.Views.Details.DetailActivity

class MatchActivity : AppCompatActivity(), MainView {

    private val presenter = MatchPresenter(this)
    private lateinit var spinnerID : String
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private var listLeague = ArrayList<LeaguesItem>()
    private var menu: Int = 1
    private var passItem : ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        allLeagues()
        callSpinner(menu)
        startBottomNav()


        var listMatches = listMatches
        listMatches.layoutManager = LinearLayoutManager(this)

        swipeRefresh = goSwipeRefresh
        swipeRefresh.setOnRefreshListener {
            if (swipeRefresh.isRefreshing) {
                swipeRefresh.isRefreshing = false
                showLoading()
                callSpinner(menu)
                containerToShow(spinnerID, menu)
                hideLoading()
            }

        }
    }

    private fun toNextActivity(eventsItem: EventsItem) {
        val idEvent = eventsItem.idEvent.toString()
        val dataMatch  = eventsItem
        passItem?.clear()
        passItem = arrayListOf(eventsItem.idHomeTeam.toString(), eventsItem.idAwayTeam.toString())
        val showDetailActivityIntent = Intent(this, DetailActivity::class.java)
        showDetailActivityIntent.putExtra("PASSEDITEM", passItem)
        showDetailActivityIntent.putExtra("ID_EVENTS", idEvent)
        showDetailActivityIntent.putExtra("TOFAV", dataMatch)
        startActivity(showDetailActivityIntent)
    }

    fun allLeagues() {

        val queue = Volley.newRequestQueue(this)
        val url: String = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var leagueList = response.toString()
                val jsonObj: JSONObject = JSONObject(leagueList)
                val jsonArray: JSONArray = jsonObj.getJSONArray("leagues")
                val lengthResponse = jsonArray.length()
                val idLeague : ArrayList<String> = ArrayList()
                val strLeague : ArrayList<String> = ArrayList()
                val soccerType = arrayOfNulls<String>(lengthResponse)
                var counList = 0
                listLeague.clear()
                if (lengthResponse > 0) {
                for (i in 0 until lengthResponse) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    soccerType[i] = jsonInner.getString("strSport")
                    if (soccerType[i] == "Soccer") {
                        idLeague.add(jsonInner.getString("idLeague"))
                        strLeague.add(jsonInner.getString("strLeague"))
                            listLeague.add(
                                LeaguesItem(
                                    strLeague[counList],
                                    idLeague[counList]
                                )
                            )
                        counList+=1
                        }
                    }
                }
                leagueSpinner.adapter = ArrayAdapter<String>(this@MatchActivity,
                    android.R.layout.simple_spinner_dropdown_item, strLeague)
            },
            Response.ErrorListener { Log.i("ERROR NIH", "league error") })
        queue.add(stringReq)
    }

    private fun callSpinner(navMenu: Int) {
        leagueSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                spinnerID = listLeague[position].idLeague!!
                Log.i("idLeague", spinnerID)
                when (navMenu) {
                    1 -> containerToShow(spinnerID, 1)
                    2 -> containerToShow(spinnerID, 2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun containerToShow(spinnerID: String, navMenu: Int) {
        var data: MutableList<EventsItem>
        if (navMenu == 1) {
            showLoading()
            presenter.getLastMatches(this, spinnerID, object : OutputServerStats {

                override fun onSuccess(response: String) {
                    Log.i("RESPONSE", response)
                    try {
                        data = presenter.parsingData(this@MatchActivity, response)
                        if (data.size < 1) {
                            Toast.makeText(this@MatchActivity, "Maaf, coba lagi", Toast.LENGTH_SHORT).show()
                        } else {
                            listMatches.adapter = MatchAdapter(data, {data -> toNextActivity(data) })
                            Log.i("DATAPARSED", data.toString())
                            hideLoading()
                        }

                    } catch (e: NullPointerException) {
                        Log.i("ERROR", "NullPointerException")
                    }
                }

                override fun onFailed(response: String) {
                    Log.i("ERROR", response)
                }

                override fun onFailure(throwable: Throwable?) {
                    Toast.makeText(this@MatchActivity, "No connection?", Toast.LENGTH_SHORT).show()
                }
            })
        } else if (navMenu == 2) {
            showLoading()
            presenter.getNextMatch(this, spinnerID, object : OutputServerStats {

                override fun onSuccess(response: String) {
                    try {
                        data = presenter.parsingData(this@MatchActivity, response)
                        if (data.size < 1) {
                            Toast.makeText(this@MatchActivity, "Maaf, coba lagi", Toast.LENGTH_SHORT).show()
                        } else {
                            listMatches.adapter = MatchAdapter(data, {data -> toNextActivity(data) })
                            hideLoading()
                        }

                    } catch (e: NullPointerException) {
                        Log.i("ERROR", "NullPointerException")
                    }

                }

                override fun onFailed(response: String) {
                    Log.i("ERROR", response)
                }

                override fun onFailure(throwable: Throwable?) {
                    Toast.makeText(this@MatchActivity, "No connection?", Toast.LENGTH_SHORT).show()
                }
            })
        } else if (menu == 3) {
            var data : MutableList<EventsItem>
            Log.i("DATAFAV", "start?")
            data = presenter.getFav(this)
            if (data.size < 1) {
                Toast.makeText(this@MatchActivity, "No Data", Toast.LENGTH_SHORT).show()
            } else {
                listMatches.adapter = MatchAdapter(data, {data -> toNextActivity(data) })
            }
        }
    }

    private fun startBottomNav() {
        navigationButton.setOnNavigationItemSelectedListener(bottomNavigationListener)
    }

    private val bottomNavigationListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            Log.i("BOTNAV", item.toString())
            when (item.itemId) {
                R.id.last_match_nav -> {
                    leagueSpinner.visibility = View.VISIBLE
                    menu = 1
                    title = getString(R.string.last)
                    callSpinner(menu)
                    containerToShow(spinnerID, menu)
                    Log.d("ACT", "Last")
                    true
                }
                R.id.next_match_nav -> {
                    leagueSpinner.visibility = View.VISIBLE
                    menu = 2
                    title = getString(R.string.next)
                    callSpinner(menu)
                    containerToShow(spinnerID, menu)
                    Log.d("ACT", "Next")
                    true
                }
                R.id.favorites -> {
                    menu = 3
                    title = getString(R.string.favorites)
                    callSpinner(menu)
                    containerToShow("0", menu)
                    Log.d("ACT", "FAV")
                    true
                }
                else -> {
                    true
                }
            }
        }
    }


    override fun showLoading() {
        proBar.visible()
    }

    override fun hideLoading() {
        proBar.invisible()
    }

    override fun getPrevEvents(dataPrev: List<EventsItem>) {

    }

    override fun showMatch(events: List<EventsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
