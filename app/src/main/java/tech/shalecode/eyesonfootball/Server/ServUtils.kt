package tech.shalecode.eyesonfootball.Server

object ServUtils {
    val apiService: RetroService
        get() = RetroCli.getClient(FootballAPI.END_POINT).create(RetroService::class.java)
}