package com.example.apidemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.apidemo.R
import com.example.apidemo.dataclasses.OpenAiDataClass
import com.example.apidemo.interfaces.OpenAiInterface
import com.example.apidemo.modelclass.OpenAiModelClass
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
                          INCOMPLETE
*/

class OpenAiActivity : AppCompatActivity() {

    lateinit var modelClass: OpenAiModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_ai)

        val editText: EditText = findViewById(R.id.edtxt_query_OpenAI)
        val textView: TextView = findViewById(R.id.txt_ai_response)

        findViewById<ImageButton>(R.id.btn_send).setOnClickListener {

            /*
             val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    // Add the bearer token to the Authorization header
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", )

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

             */


            val urlBuilder = Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
//                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenAiInterface::class.java)

            modelClass = OpenAiModelClass("code-davinci-002", editText.text.toString(), 0, 256, 1, 0, 0)
            val jsonData = Gson().toJson(modelClass)
            Log.d("Model Class", modelClass.toString())
            Log.d("Json data", jsonData.toString())

            urlBuilder.postQuery("application/json", "Bearer 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", modelClass)
                .enqueue(object : Callback<List<OpenAiDataClass>?> {
                    override fun onResponse(call: Call<List<OpenAiDataClass>?>, response: Response<List<OpenAiDataClass>?>) {
                        Log.d("URL", response.toString())

                        val responseBody = response.body()!!
                        textView.text = responseBody[0].choices[0].text
                    }

                    override fun onFailure(call: Call<List<OpenAiDataClass>?>, t: Throwable) {
                        Log.d("RESPONSE FAIL", t.message.toString())
                    }
                })

        }

    }
}
