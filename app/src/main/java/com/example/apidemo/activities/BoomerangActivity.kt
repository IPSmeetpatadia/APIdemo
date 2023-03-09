package com.example.apidemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apidemo.R
import com.example.apidemo.dataclasses.boomerang.BoomerangApiDataClass
import com.example.apidemo.interfaces.BoomerangApiInterface
import com.example.apidemo.modelclass.BoomerangModelClass1
import com.example.apidemo.modelclass.BoomerangModelClass2
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_boomerang.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoomerangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boomerang)

        btn_submitDetails.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://request-boomerang.p.rapidapi.com/")
            .build()
            .create(BoomerangApiInterface::class.java)

        val queryModelClas1 = BoomerangModelClass1(edtxt_empName.text.toString())
        val queryJsonData = Gson().toJson(queryModelClas1)

        val queryModelClas2 = BoomerangModelClass2(ID = edtxt_empID.text.toString(), Designation = edtxt_empDesignation.text.toString())

        urlBuilder.getData(data = queryJsonData, modelClass2 = queryModelClas2, "83e0bb9359mshf23a5e69a002769p129d96jsn8ed0c455bfcf", "request-boomerang.p.rapidapi.com")
            .enqueue(object : Callback<BoomerangApiDataClass?> {
                override fun onResponse(call: Call<BoomerangApiDataClass?>, response: Response<BoomerangApiDataClass?>) {
                    val responseBody = response.body()

                    val resultToShow = StringBuilder()
                    resultToShow.append("Name: " + responseBody?.EmpName + "\n\n")
                    resultToShow.append("ID: " + responseBody?.`__`?.body?.ID + "\n")
                    resultToShow.append("Designation: " + responseBody?.`__`?.body?.Designation + "\n\n")

                    txt_boomerangResponse.text = resultToShow
                }

                override fun onFailure(call: Call<BoomerangApiDataClass?>, t: Throwable) {
                    Log.d("getData()", "Response Fail")
                }
            })
    }
}