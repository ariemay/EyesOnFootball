package tech.shalecode.eyesonfootball.Views.Details

/*

Originally Made by Arie May Wibowo
With several resources as reference for RETROFIT2
For Dicoding Submission 2
No plagiation I did. Don't ban me.
Thank you.

shalecode.tech => My personal domain, not released yet. Still trying to use Django for it.

 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONObject
import tech.shalecode.eyesonfootball.Presenter.DetailPresenter
import tech.shalecode.eyesonfootball.R
import tech.shalecode.eyesonfootball.Utility.OutputServerStats

class DetailActivity : AppCompatActivity() {

    private val presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val homeName = HOME_NAME
        val homeScore = HOME_SCORE
        val awayName = AWAY_NAME
        val awayScore = AWAY_SCORE
        val homeScorer = HOME_SCORERS
        val awayScorer = AWAY_SCORERS
        val homeYellow = HOME_YELLOW
        val awayYellow = AWAY_YELLOW
        val homeRed = HOME_RED
        val awayRed = AWAY_RED
        val homeForward = HOME_FORWARD
        val awayForward = AWAY_FORWARD
        val homeMidfielder = HOME_MIDFIELDER
        val awayMidfielder = AWAY_MIDFIELDER
        val homeDefender = HOME_DEFENDER
        val awayDefender = AWAY_DEFENDER
        val homeKeeper = HOME_KEEPER
        val awayKeeper = AWAY_KEEPER

        val eventsId = intent.getStringExtra("EVENT_ID")

        presenter.getDetailMatch(this, eventsId, object: OutputServerStats {
            override fun onFailed(response: String) {
                Toast.makeText(this@DetailActivity, "Failed. Coba lagi", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(throwable: Throwable?) {
                Toast.makeText(this@DetailActivity, "Check your connection", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(response: String) {
                val jsonObject = JSONObject(response)
                val event = jsonObject.getJSONArray("events")
                for( i in 0 until event.length()) {
                    val events = event.getJSONObject(i)
                    if (events.getString("intHomeScore") != "null") {
                        homeName.text = events.getString("strHomeTeam")
                        homeScore.text = events.getString("intHomeScore")
                        awayName.text = events.getString("strAwayTeam")
                        awayScore.text = events.getString("intAwayScore")
                        homeScorer.text = events.getString("strHomeGoalDetails")
                        awayScorer.text = events.getString("strAwayGoalDetails")
                        homeYellow.text = events.getString("strHomeYellowCards")
                        awayYellow.text = events.getString("strAwayYellowCards")
                        homeRed.text = events.getString("strHomeRedCards")
                        awayRed.text = events.getString("strAwayRedCards")
                        homeForward.text = events.getString("strHomeLineupForward")
                        awayForward.text = events.getString("strAwayLineupForward")
                        homeMidfielder.text = events.getString("strHomeLineupMidfield")
                        awayMidfielder.text = events.getString("strAwayLineupMidfield")
                        homeDefender.text = events.getString("strHomeLineupDefense")
                        awayDefender.text = events.getString("strAwayLineupDefense")
                        homeKeeper.text = events.getString("strHomeLineupGoalkeeper")
                        awayKeeper.text = events.getString("strAwayLineupGoalkeeper")
                    } else {
                        homeName.text = events.getString("strHomeTeam")
                        homeScore.text = "?"
                        awayName.text = events.getString("strAwayTeam")
                        awayScore.text = "?"
                    }
                }
            }
        })
    }
}
