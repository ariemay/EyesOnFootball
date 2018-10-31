package tech.shalecode.eyesonfootball.Models

import com.google.gson.annotations.SerializedName

data class LeagueList(

	@SerializedName("leagues")
	val leagues: List<LeaguesItem>
)