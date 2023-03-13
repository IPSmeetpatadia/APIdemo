package com.example.apidemo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidemo.R
import com.example.apidemo.adapters.DummyDataAdapter
import com.example.apidemo.dataclasses.dummyEMP.DummyDataClass
import com.example.apidemo.dataclasses.dummyEMP.Users
import com.example.apidemo.interfaces.DummyDataInterface
import kotlinx.android.synthetic.main.activity_dummy_data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DummyDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_data)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/")
            .client(client)
            .build()
            .create(DummyDataInterface::class.java)

        urlBuilder.getEmpDetails(100)
            .enqueue(object : Callback<DummyDataClass?> {
                override fun onResponse(call: Call<DummyDataClass?>, response: Response<DummyDataClass?>) {
                    val responseBody = response.body()
                    Log.d("RESPONSE", responseBody.toString())

                    recyclerView_storeEmployees.apply {
                        layoutManager = LinearLayoutManager(this@DummyDataActivity, LinearLayoutManager.VERTICAL, false)
                        adapter = DummyDataAdapter(this@DummyDataActivity, responseBody!!, object : DummyDataAdapter.OnItemClick{
                            override fun itemClicked(empDetail: Users) {
                                val intent = Intent(this@DummyDataActivity, UserDetailsActivity::class.java)
                                intent.putExtra("userID", empDetail.id.toString())
                                startActivity(intent)
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<DummyDataClass?>, t: Throwable) {
                    Log.d("getEmpDetails ~ RESPONSE FAIL", t.message.toString())
                }
            })
    }
}