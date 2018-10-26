package tech.shalecode.eyesonfootball.Server

import tech.shalecode.eyesonfootball.BuildConfig

object FootballAPI {
    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/search_all_teams.php?l=" + league
    }
    fun getNextSchedule(leagueID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=" + leagueID
    }
    fun getLastSchedule(leagueID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" + leagueID
    }fun getDetailMatch(EventID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupevent.php?id=" + EventID
    }
}