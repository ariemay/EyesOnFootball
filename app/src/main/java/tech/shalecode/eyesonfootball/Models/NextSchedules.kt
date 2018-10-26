package tech.shalecode.eyesonfootball.Models

import com.google.gson.annotations.SerializedName

data class NextSchedules (
    @SerializedName("strEvent")
    var nextTeam2Team: String? = null,

    @SerializedName("dateEvent")
    var nextMatchSched: String? = null,

    @SerializedName("idEvent")
    var eventID: Int? = null
)