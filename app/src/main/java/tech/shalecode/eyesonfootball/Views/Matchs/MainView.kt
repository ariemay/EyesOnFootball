package tech.shalecode.eyesonfootball.Views.Matchs

import tech.shalecode.eyesonfootball.Models.EventsItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatch (events: List<EventsItem>)
    fun getPrevEvents (dataPrev: List<EventsItem>)
}