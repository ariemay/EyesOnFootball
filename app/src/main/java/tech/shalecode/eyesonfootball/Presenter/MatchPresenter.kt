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
                            callback.onSuccess(response.body().string())
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
                            callback.onSuccess(response.body().string())
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
            Log.i("JSONARRAY", message.toString())
            for (i in 0 until message.length()) {
                val data = message.getJSONObject(i)
                val dateEvent = data.getString("dateEvent")
                val idAwayTeam= data.getString("idAwayTeam")
                val idEvent= data.getString("idEvent")
                val idHomeTeam= data.getString("idHomeTeam")
                val idLeague= data.getString("idLeague")
                val idSoccerXML= data.getString("idSoccerXML")
                val intAwayScore= data.getString("intAwayScore")
                val intAwayShots= data.getString("intAwayShots")
                val intHomeScore= data.getString("intHomeScore")
                val intHomeShots= data.getString("intHomeShots")
                val intRound= data.getString("intRound")
                val intSpectators= data.getString("intSpectators")
                val strAwayFormation= data.getString("strAwayFormation")
                val strAwayGoalDetails= data.getString("strAwayGoalDetails")
                val strAwayLineupDefense= data.getString("strAwayLineupDefense")
                val strAwayLineupForward= data.getString("strAwayLineupForward")
                val strAwayLineupGoalkeeper= data.getString("strAwayLineupGoalkeeper")
                val strAwayLineupMidfield= data.getString("strAwayLineupMidfield")
                val strAwayLineupSubstitutes= data.getString("strAwayLineupSubstitutes")
                val strAwayRedCards= data.getString("strAwayRedCards")
                val strAwayTeam= data.getString("strAwayTeam")
                val strAwayYellowCards= data.getString("strAwayYellowCards")
                val strBanner= data.getString("strBanner")
                val strCircuit= data.getString("strCircuit")
                val strCity= data.getString("strCity")
                val strCountry= data.getString("strCountry")
                val strDate= data.getString("strDate")
                val strDescriptionEN= data.getString("strDescriptionEN")
                val strEvent= data.getString("strEvent")
                val strFanart= data.getString("strFanart")
                val strFilename= data.getString("strFilename")
                val strHomeFormation= data.getString("strHomeFormation")
                val strHomeGoalDetails= data.getString("strHomeGoalDetails")
                val strHomeLineupDefense= data.getString("strHomeLineupDefense")
                val strHomeLineupForward= data.getString("strHomeLineupForward")
                val strHomeLineupGoalkeeper= data.getString("strHomeLineupGoalkeeper")
                val strHomeLineupMidfield= data.getString("strHomeLineupMidfield")
                val strHomeLineupSubstitutes= data.getString("strHomeLineupSubstitutes")
                val strHomeRedCards= data.getString("strHomeRedCards")
                val strHomeTeam= data.getString("strHomeTeam")
                val strHomeYellowCards= data.getString("strHomeYellowCards")
                val strLeague= data.getString("strLeague")
                val strLocked= data.getString("strLocked")
                val strMap= data.getString("strMap")
                val strPoster= data.getString("strPoster")
                val strResult= data.getString("strResult")
                val strSeason= data.getString("strSeason")
                val strSport= data.getString("strSport")
                val strTVStation= data.getString("strTVStation")
                val strThumb= data.getString("strThumb")
                val strTime= data.getString("strTime")

                dataList.add(
                        EventsItem(dateEvent, idAwayTeam,
                                idEvent,
                                idHomeTeam,
                                idLeague,
                                idSoccerXML,
                                intAwayScore,
                                intAwayShots,
                                intHomeScore,
                                intHomeShots,
                                intRound,
                                intSpectators,
                                strAwayFormation,
                                strAwayGoalDetails,
                                strAwayLineupDefense,
                                strAwayLineupForward,
                                strAwayLineupGoalkeeper,
                                strAwayLineupMidfield,
                                strAwayLineupSubstitutes,
                                strAwayRedCards,
                                strAwayTeam,
                                strAwayYellowCards,
                                strBanner,
                                strCircuit,
                                strCity,
                                strCountry,
                                strDate,
                                strDescriptionEN,
                                strEvent,
                                strFanart,
                                strFilename,
                                strHomeFormation,
                                strHomeGoalDetails,
                                strHomeLineupDefense,
                                strHomeLineupForward,
                                strHomeLineupGoalkeeper,
                                strHomeLineupMidfield,
                                strHomeLineupSubstitutes,
                                strHomeRedCards,
                                strHomeTeam,
                                strHomeYellowCards,
                                strLeague,
                                strLocked,
                                strMap,
                                strPoster,
                                strResult,
                                strSeason,
                                strSport,
                                strTVStation,
                                strThumb,
                                strTime
                ))
                Log.i("DATALIST", dataList.toString())
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