package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidemo.adapters.RetrofitAdapter
import com.example.apidemo.dataclasses.RetrofitDataClassItem
import com.example.apidemo.interfaces.RetrofitInterface
import kotlinx.android.synthetic.main.activity_retrofit_apiactivity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class RetrofitAPIActivity : AppCompatActivity() {

    lateinit var retrofitAdapter: RetrofitAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_apiactivity)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView_retrofit.layoutManager = linearLayoutManager

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

                retrofitAdapter = RetrofitAdapter(baseContext, responseBody)
                retrofitAdapter.notifyDataSetChanged()

                recyclerView_retrofit.adapter = retrofitAdapter

                /*
                //setting coming data into textview

                val stringBuilder = StringBuilder()
                for (myData in responseBody){
                    stringBuilder.append(myData.title)
                    stringBuilder.append("\n\n")
                }
                txtRetroDataDisplay.text = stringBuilder
                */
            }

            override fun onFailure(call: Call<List<RetrofitDataClassItem>?>, t: Throwable) {
                Log.d("RetrofitAPIActivity", "onFailure: " + t.message)
            }
        })
    }

}