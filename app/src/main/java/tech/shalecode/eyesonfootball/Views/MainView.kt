package tech.shalecode.eyesonfootball.Views

import tech.shalecode.eyesonfootball.Models.EventsItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatch (events: List<EventsItem>)
}