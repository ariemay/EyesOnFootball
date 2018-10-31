package tech.shalecode.eyesonfootball.Server

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject
import tech.shalecode.eyesonfootball.Models.LeaguesItem

class JSONGetter () {

    // function for network call
    fun getAPI() {
        // Instantiate the RequestQueue.
        var listLeague = ArrayList<LeaguesItem>()

        val url: String = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var leagueList = response.toString()
                val jsonObj: JSONObject = JSONObject(leagueList)
                val jsonArray: JSONArray = jsonObj.getJSONArray("leagues")
                val lengthResponse = jsonArray.length()
                val idLeague = arrayOfNulls<String>(lengthResponse)
                val leagueName = arrayOfNulls<String>(lengthResponse)
                for (i in 0 until lengthResponse) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    idLeague[i] = jsonInner.get("idLeague") as String
                    leagueName[i] = jsonInner.get("strLeague") as String
                    listLeague.add(LeaguesItem(idLeague[i].toString(),
                        leagueName[i].toString()))
                }
            },
            Response.ErrorListener { Log.i("ERROR NIH", "league error") })
    }
}