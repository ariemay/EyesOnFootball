package tech.shalecode.eyesonfootball.Models

import com.google.gson.annotations.SerializedName

data class MatchResponse(

	@SerializedName("events")
	val events: List<EventsItem>
)