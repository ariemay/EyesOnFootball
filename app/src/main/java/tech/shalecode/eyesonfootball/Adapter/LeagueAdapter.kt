package tech.shalecode.eyesonfootball.Adapter

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class LeagueAdapter {

    fun getLeagues(){
        val url="https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"
        MyAsyncTask().execute(url)
    }


    inner  class  MyAsyncTask: AsyncTask<String, String, String>(){

        override fun doInBackground(vararg p0: String?): String {
            try {
                val url= URL(p0[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout=500

                val dataJsonAsString=convertStreanToString(urlConnect.inputStream)
                publishProgress(dataJsonAsString)
                val json= JSONObject(dataJsonAsString)

                Log.i("TRYING HARDER", json.getString("idLeague"))
                ///here is the required array
//                val availabilityArray = json.getJSONArray("availability")
//                val query= json.getJSONObject("identification")ecl
//                val sunrise=query.getString("id")
            }catch (ex:Exception){

            }

            return  ""
        }


        override fun onProgressUpdate(vararg values:String?) {

        }

        fun convertStreanToString(inputStream: InputStream):String{
            val  bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line:String
            var allstring:String=""

            try {
                do {
                    line=bufferReader.readLine()
                    if(line!=null)
                        allstring+=line
                }while (line!=null)
                bufferReader.close()
            }catch (ex:Exception){}


            return allstring
        }

    }

}