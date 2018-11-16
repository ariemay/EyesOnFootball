package tech.shalecode.eyesonfootball.Presenter

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import okhttp3.ResponseBody
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.shalecode.eyesonfootball.Helper.database
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Server.RetroService
import tech.shalecode.eyesonfootball.Server.ServUtils
import tech.shalecode.eyesonfootball.Utility.OutputServerStats
import tech.shalecode.eyesonfootball.Views.Details.DetailInterface

class DetailPresenter : DetailInterface {
    override fun isSuccess(response: String): Boolean {
        var success = true
        try {
            val jObj = JSONObject(response)
            success = jObj.getBoolean("success")
        } catch (e: Exception) {

        }
        return success
    }

    override fun isFavorite(context: Context, data: EventsItem): Boolean {
        var bFavorite = false

        context.database.use {
            val favorites = select(EventsItem.TABLE_FAVORITES)
                .whereArgs(EventsItem.ID_EVENT + " = {id}",
                    "id" to data.idEvent.toString())
                .parseList(classParser<EventsItem>())

            bFavorite = !favorites.isEmpty()
        }

        return bFavorite
    }

    fun getDetailMatch(context: Activity, eventID: String?, callback: OutputServerStats) {
        val newRetroServ: RetroService = ServUtils.apiService
        if (eventID != null) {
            newRetroServ.getEventDetail(eventID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().string())
                        } else {
                            callback.onFailed(response.errorBody().string())
                        }
                    }
                }
            })
        }
    }
    fun getBadgeForDetail(context: Activity, teamID: String?, callback: OutputServerStats) {
        val newRetroServ: RetroService = ServUtils.apiService
        if (teamID != null) {
            newRetroServ.getTeamBadge(teamID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().string())
                        } else {
                            callback.onFailed(response.errorBody().string())
                        }
                    }
                }
            })
        }
    }
    override fun addFav(context: Context, data: EventsItem) {
        try {
            context.database.use {
                insert(EventsItem.TABLE_FAVORITES,
                    EventsItem.ID to data.id,
                    EventsItem.DATE_EVENT to data.dateEvent,
                    EventsItem.ID_AWAY_TEAM to data.idAwayTeam,
                    EventsItem.ID_EVENT to data.idEvent,
//                    EventsItem.STR_SPORT to data.strSport,
                    EventsItem.ID_HOME_TEAM to data.idHomeTeam,
                    EventsItem.ID_LEAGUE to data.idLeague,
//                    EventsItem.HOME_FORMATION to data.strHomeFormation,
//                    EventsItem.HOME_GOAL_DETAILS to data.strHomeGoalDetails,
//                    EventsItem.HOME_SHOTS to data.intHomeShots,
//                    EventsItem.HOME_LINEUP_GOALKEEPER to data.strHomeLineupGoalkeeper,
//                    EventsItem.HOME_LINEUP_DEFENSE to data.strHomeLineupDefense,
//                    EventsItem.HOME_LINEUP_MIDFIELD to data.strHomeLineupMidfield,
//                    EventsItem.HOME_LINEUP_FORWARD to data.strHomeLineupForward,
//                    EventsItem.HOME_LINEUP_SUBSTITUTES to data.strHomeLineupSubstitutes,
                    EventsItem.INT_AWAY_SCORE to data.intAwayScore,
                    EventsItem.INT_HOME_SCORE to data.intHomeScore,
                    EventsItem.STR_AWAY_TEAM to data.strAwayTeam,
                    EventsItem.STR_HOME_TEAM to data.strHomeTeam
                    )
//                    EventsItem.AWAY_FORMATION to data.strAwayFormation,
//                    EventsItem.AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
//                    EventsItem.AWAY_SHOTS to data.intAwayShots,
//                    EventsItem.AWAY_LINEUP_GOALKEEPER to data.strAwayLineupGoalkeeper,
//                    EventsItem.AWAY_LINEUP_DEFENSE to data.strAwayLineupDefense,
//                    EventsItem.AWAY_LINEUP_MIDFIELD to data.strAwayLineupMidfield,
//                    EventsItem.AWAY_LINEUP_FORWARD to data.strAwayLineupForward,
//                    EventsItem.AWAY_LINEUP_SUBSTITUTES to data.strAwayLineupSubstitutes)
            }
        } catch (e: SQLiteConstraintException) {
            context.toast("Error: ${e.message}")
        }
    }
    override fun removeFav(context: Context, data: EventsItem) {
        try {
            context.database.use {
                delete(EventsItem.TABLE_FAVORITES,
                    EventsItem.ID_EVENT + " = {id}",
                    "id" to data.idEvent.toString())
            }
        } catch (e: SQLiteConstraintException) {
            Log.i("RemovingError", e.toString())
        }
    }
}