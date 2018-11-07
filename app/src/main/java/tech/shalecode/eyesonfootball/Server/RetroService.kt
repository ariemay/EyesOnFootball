package tech.shalecode.eyesonfootball.Server

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET(FootballAPI.eventsPastleague)
    fun getPastMatches(@Query("id") leagueID: String) : Call<ResponseBody>

    @GET(FootballAPI.eventsNextleague)
    fun getNextMatches(@Query("id") leagueID: String) : Call<ResponseBody>

    @GET(FootballAPI.lookupEvent)
    fun getEventDetail(@Query("id") eventID: String) : Call<ResponseBody>

    @GET(FootballAPI.lookupteam)
    fun getTeamBadge(@Query("id") eventID: String) : Call<ResponseBody>
}