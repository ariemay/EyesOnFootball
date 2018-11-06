package tech.shalecode.eyesonfootball.Presenter

import android.app.Activity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.shalecode.eyesonfootball.Server.RetroService
import tech.shalecode.eyesonfootball.Server.ServUtils
import tech.shalecode.eyesonfootball.Utility.OutputServerStats
import tech.shalecode.eyesonfootball.Views.Details.DetailActivity

class DetailPresenter (private val view: DetailActivity) {
    fun getDetailMatch(context: Activity, eventID: String?, callback: OutputServerStats) {
        val newRetroServ: RetroService = ServUtils.apiService
        if (eventID != null) {
            newRetroServ.getEventDetail(eventID).enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    callback.onFailure(t)
                }
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body().string())
                        } else {
                            callback.onFailed(response.errorBody().string())
                        }
                    }
                }
            })
        }
    }
}