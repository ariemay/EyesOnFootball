package tech.shalecode.eyesonfootball.Models

import com.google.gson.annotations.SerializedName

data class LastSchedules (
    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: Int? = null,

    @SerializedName("intAwayScore")
    var awayScore: Int? = null,

    @SerializedName("dateEvent")
    var lastMatchDate: String? = null
)