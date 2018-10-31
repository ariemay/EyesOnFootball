package tech.shalecode.eyesonfootball.Views

import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Models.LeaguesItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatch (events: List<EventsItem>)
    fun showLeagueID (dataLeagues: List<LeaguesItem>)
    fun getPrevEvents (dataPrev: List<EventsItem>)
}