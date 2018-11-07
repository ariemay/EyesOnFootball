package tech.shalecode.eyesonfootball.Server

import tech.shalecode.eyesonfootball.BuildConfig

class FootballAPI {

    companion object {
        // base end point
        const val END_POINT = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"

        const val eventsPastleague = "eventspastleague.php"

        const val eventsNextleague = "eventsnextleague.php"

        const val lookupEvent = "lookupevent.php"

        const val lookupteam = "lookupteam.php"
    }
}