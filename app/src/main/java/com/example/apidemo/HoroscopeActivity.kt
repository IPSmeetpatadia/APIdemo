package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HoroscopeActivity : AppCompatActivity(), AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    var sunSign = "-- Select Your Sign--"
    var resultView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)

        var btn: Button = findViewById(R.id.btn_getHoroscope)
        btn.setOnClickListener {
            GlobalScope.async {
                getPredictions(btn)
            }
        }

        val spinner = findViewById<Spinner>(R.id.spinner_sunSign)
        val adapter = ArrayAdapter.createFromResource(this,R.array.sunSign,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter;
        spinner.onItemSelectedListener = this

        resultView = findViewById(R.id.resultView)
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    /*
        SELECTION EVENT HANDLERS
    */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            sunSign = parent.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        sunSign = "-- Select Your Sign--"
    }

    /*
        API call
    */
    suspend fun getPredictions(view: View) {
        try {
            val result = GlobalScope.async {
                callAztroAPI("https://sameer-kumar-aztro-v1.p.rapidapi.com/?sign=" + sunSign + "&day=today")
            }.await()

            onResponse(result)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun callAztroAPI(apiUrl:String ):String?{
        var result: String? = ""
        val url: URL
        var connection: HttpURLConnection?
        try {
            url = URL(apiUrl)
            connection = url.openConnection() as HttpURLConnection

            // SET HEADERS FOR THE REQUEST
            // set host name
            connection.setRequestProperty("x-rapidapi-host", "sameer-kumar-aztro-v1.p.rapidapi.com")
            // set the rapid-api key
            connection.setRequestProperty("x-rapidapi-key", "83e0bb9359mshf23a5e69a002769p129d96jsn8ed0c455bfcf")
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded")
            // set the request method
            connection.requestMethod = "POST"
            val `in` = connection.inputStream
            val reader = InputStreamReader(`in`)

            // read the response data
            var data = reader.read()
            while (data != -1) {
                val current = data.toChar()
                result += current
                data = reader.read()
            }
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // if not able to retrieve data return null
        return null
    }

    /*
         DISPLAY HOROSCOPE PREDICTION EXTRACTED FROM TH API RESPONSE
    */
    private fun onResponse(result: String?) {
        try {
            // convert the string to JSON object for better reading
            val resultJson = JSONObject(result)

            // Initialize prediction text
            var prediction ="Today's prediction \n\n"
            prediction += this.sunSign+"\n"

            // Update text with various fields from response
            prediction += resultJson.getString("date_range")+"\n\n"
            prediction += resultJson.getString("description")

            //Update the prediction to the view
            setText(this.resultView,prediction)

        } catch (e: Exception) {
            e.printStackTrace()
            this.resultView!!.text = "Oops!! something went wrong, please try again"
        }
    }

    private fun setText(text: TextView?, value: String) {
        runOnUiThread { text!!.text = value }
    }
}