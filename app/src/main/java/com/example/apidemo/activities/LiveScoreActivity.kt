package com.example.apidemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apidemo.R
import com.example.apidemo.dataclasses.livescore.LiveScoreDataClass
import com.example.apidemo.interfaces.LiveScoreInterface
import kotlinx.android.synthetic.main.activity_live_score.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LIVE_BASE_URL = "https://livescore6.p.rapidapi.com/matches/v2/"

class LiveScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_score)
        getLiveScore()
    }

    private fun getLiveScore() {
        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(LIVE_BASE_URL)
            .build()
            .create(LiveScoreInterface::class.java)

        val liveData = urlBuilder.getLiveScore("soccer", 702093, "83e0bb9359mshf23a5e69a002769p129d96jsn8ed0c455bfcf", "livescore6.p.rapidapi.com")

        liveData.enqueue(object : Callback<LiveScoreDataClass> {
            override fun onResponse(call: Call<LiveScoreDataClass>, response: Response<LiveScoreDataClass>) {
                val responseBody = response.body()!!

                var stringBuilder = StringBuilder()
                stringBuilder.append(responseBody.Eid)
                stringBuilder.append("\n")
                stringBuilder.append(responseBody.Esd)
                stringBuilder.append("\n")
                stringBuilder.append(responseBody.Vneut)
                stringBuilder.append("\n")
                stringBuilder.append(responseBody.Vsp)
                stringBuilder.append("\n")
                stringBuilder.append(responseBody.Vnm)
                txtLive1.setOnClickListener {
                    txtLive2.text = responseBody.Refs.toString()
                }

                txtLive1.text = stringBuilder
            }

            override fun onFailure(call: Call<LiveScoreDataClass>, t: Throwable) {
                Log.d("RetrofitAPIActivity", "onFailure: " + t.message)
            }
        })
    }
}