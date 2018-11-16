package tech.shalecode.eyesonfootball.Views.Details

import android.app.Activity
import android.content.Context
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.Utility.OutputServerStats

interface DetailInterface {
    fun isSuccess(response: String): Boolean
    fun addFav(context: Context, data: EventsItem)
    fun removeFav(context: Context, data: EventsItem)
    fun isFavorite(context: Context, data: EventsItem): Boolean
}