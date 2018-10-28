package tech.shalecode.eyesonfootball.Presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import tech.shalecode.eyesonfootball.Models.LeagueList
import tech.shalecode.eyesonfootball.Models.MatchResponse
import tech.shalecode.eyesonfootball.Server.ApiRepository
import tech.shalecode.eyesonfootball.Server.FootballAPI
import tech.shalecode.eyesonfootball.Views.MainView

class MatchPresenter (private val view: MainView,
                      private val apiRepo: ApiRepository,
                      private val gson: Gson) {
    fun getLeagues() {
        doAsync {
            val dataLeagues = gson.fromJson(apiRepo.doRequest(FootballAPI.getLeagues()),
                LeagueList::class.java)
        }
    }
    fun getPrevMatch(idLeague: String?) {
        doAsync {
            val dataPrev = gson.fromJson(apiRepo.doRequest(FootballAPI.getLastSchedule(idLeague)),
                MatchResponse::class.java)
        }
    }

    fun getNextMatch(idLeague: String?) {
        doAsync {
            val dataNext = gson.fromJson(apiRepo.doRequest(FootballAPI.getNextSchedule(idLeague)),
                MatchResponse::class.java)
        }
    }

    fun getDetailMatch(eventID : String?) {
        doAsync {
            val dataDetails = gson.fromJson(apiRepo.doRequest(FootballAPI.getDetailMatch(eventID)),
                MatchResponse::class.java)
        }
    }
}