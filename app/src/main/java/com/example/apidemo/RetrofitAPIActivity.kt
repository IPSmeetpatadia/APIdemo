package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.apidemo.dataclasses.RetrofitDataClassItem
import com.example.apidemo.interfaces.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class RetrofitAPIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_apiactivity)

        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<RetrofitDataClassItem>?> {
            override fun onResponse(call: Call<List<RetrofitDataClassItem>?>, response: Response<List<RetrofitDataClassItem>?>) {
                val responseBody = response.body()!!

                val stringBuilder = StringBuilder()
                for (myData in responseBody){
                    stringBuilder.append(myData.title)
                    stringBuilder.append("\n\n")
                }
                findViewById<TextView>(R.id.txtRetroDataDisplay).text = stringBuilder
            }

            override fun onFailure(call: Call<List<RetrofitDataClassItem>?>, t: Throwable) {
                Log.d("RetrofitAPIActivity", "onFailure: " + t.message)
            }
        })
    }
}