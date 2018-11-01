package tech.shalecode.eyesonfootball.Presenter

import android.app.Activity
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Server.RetroService
import tech.shalecode.eyesonfootball.Server.ServUtils
import tech.shalecode.eyesonfootball.Utility.OutputServerStats
import tech.shalecode.eyesonfootball.Views.MainView

class MatchPresenter (private val view: MainView) {

    fun getLastMatches(context: Activity, leagueID: String?, callback: OutputServerStats) {
        view.showLoading()
        val newRetroServ: RetroService = ServUtils.apiService
        if (leagueID != null) {
            newRetroServ.getPastMatches(leagueID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().toString())
                        } else {
                            callback.onFailed(response.errorBody().toString())
                        }
                    }
                }
            })
        }
        view.hideLoading()
    }

    fun getNextMatch(context: Activity, leagueID: String?, callback: OutputServerStats) {
        view.showLoading()
        val newRetroServ: RetroService = ServUtils.apiService
        if (leagueID != null) {
            newRetroServ.getNextMatches(leagueID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().toString())
                        } else {
                            callback.onFailed(response.errorBody().toString())
                        }
                    }
                }
            })
        }
        view.hideLoading()
    }

    fun parsingData(context: Activity, response: String): ArrayList<EventsItem> {
        val dataList: ArrayList<EventsItem> = ArrayList()
        try {
            val jsonObject = JSONObject(response)
            Log.d("TAG", "Response error exception $jsonObject")
            val message = jsonObject.getJSONArray("events")
            for (i in 0 until message.length()) {
                val data = message.getJSONObject(i)
                val idEvent = data.getString("idEvent")
                val dateEvent = data.getString("dateEvent")
                val strSport = data.getString("strSport")
                val idHome = data.getString("idHomeTeam")
                val nameHome = data.getString("strHomeTeam")
                val scoreHome = data.getString("intHomeScore")
                val formationHome = data.getString("strHomeFormation")
                val golDetailHome = data.getString("strHomeGoalDetails")
                val intShotHome = data.getString("intHomeShots")
                val lineupgkHome = data.getString("strHomeLineupGoalkeeper")
                val lineupdefHome = data.getString("strHomeLineupDefense")
                val lineupmidhome = data.getString("strHomeLineupMidfield")
                val lineupforwadHome = data.getString("strHomeLineupForward")
                val lineupsubHome = data.getString("strHomeLineupSubstitutes")
                val idAway = data.getString("idAwayTeam")
                val nameAway = data.getString("strAwayTeam")
                val scoreAway = data.getString("intAwayScore")
                val formationAway = data.getString("strAwayFormation")
                val golDetailAway = data.getString("strAwayGoalDetails")
                val intShotAway = data.getString("intAwayShots")
                val lineupgkAway = data.getString("strAwayLineupGoalkeeper")
                val lineupdefAway = data.getString("strAwayLineupDefense")
                val lineupmidAway = data.getString("strAwayLineupMidfield")
                val lineupforwadAway = data.getString("strAwayLineupForward")
                val lineupsubAway = data.getString("strAwayLineupSubstitutes")

                dataList.add(EventsItem(i.toLong(), idEvent, dateEvent, strSport, idHome, nameHome, scoreHome, formationHome, golDetailHome, intShotHome, lineupgkHome, lineupdefHome, lineupmidhome, lineupforwadHome, lineupsubHome,
                    idAway, nameAway, scoreAway, formationAway, golDetailAway, intShotAway, lineupgkAway, lineupdefAway, lineupmidAway, lineupforwadAway, lineupsubAway
                ))
            }
        } catch (e: Exception) {
            Log.d("TAG", "Response error exception $e")
        }
        return dataList
    }

    fun getDetailMatch(context: Activity, leagueID: String?, callback: OutputServerStats) {
        view.showLoading()
        val newRetroServ: RetroService = ServUtils.apiService
        if (leagueID != null) {
            newRetroServ.getNextMatches(leagueID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().toString())
                        } else {
                            callback.onFailed(response.errorBody().toString())
                        }
                    }
                }
            })
        }
        view.hideLoading()
    }
}