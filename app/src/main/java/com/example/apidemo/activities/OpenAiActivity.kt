package com.example.apidemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import com.example.apidemo.R
import com.example.apidemo.dataclasses.openai.OpenAiDataClass
import com.example.apidemo.interfaces.OpenAiInterface
import com.example.apidemo.modelclass.OpenAiModelClass
import kotlinx.android.synthetic.main.activity_open_ai.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OpenAiActivity : AppCompatActivity() {

    private lateinit var modelClass: OpenAiModelClass
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_ai)

        title = "Open Ai"

        val editText: EditText = findViewById(R.id.edtxt_query_OpenAI)

        findViewById<ImageButton>(R.id.btn_send).setOnClickListener {

             val httpClient = OkHttpClient.Builder()
                 .readTimeout(9, TimeUnit.MINUTES)
                 .build()

            val urlBuilder = Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenAiInterface::class.java)

            modelClass = OpenAiModelClass("code-davinci-002", prompt = editText.text.toString(), 0, 256, 1, 0, 0)

            urlBuilder.postQuery(modelClass,"Bearer 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")   //  The `token` here is `Bearer` token so we need to add "Bearer" before token-key
                .enqueue(object : Callback<OpenAiDataClass> {
                    override fun onResponse(call: Call<OpenAiDataClass>, response: Response<OpenAiDataClass>) {
                        Log.d("URL", response.toString())

                        val responseBody = response.body()!!

                        /*
                        var clearResponse = ""
                        var finalResponse = responseBody.choices[0].text
                        clearResponse = finalResponse.replace("/\\(/g"," ")
                        Log.d("Response body", clearResponse)
                        */

                        txt_ai_response.text = responseBody.choices[0].text
                    }

                    override fun onFailure(call: Call<OpenAiDataClass>, t: Throwable) {
                        Log.d("RESPONSE FAIL", t.message.toString())
                    }
                })

        }

    }
}
