package com.example.apidemo.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.apidemo.R
import com.example.apidemo.dataclasses.OpenAiDataClass
import com.example.apidemo.interfaces.OpenAiInterface
import com.example.apidemo.interfaces.RetrofitHelper
import com.example.apidemo.modelclass.OpenAiModelClass
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .header(
                            "Authorization",
                            "Bearer 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"
                        )
                        .method(original.method(), original.body())
                        .build()
                    chain.proceed(request)
                }
                .build()

           /* val urlBuilder = Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(OpenAiInterface::class.java)*/

            modelClass = OpenAiModelClass("code-davinci-002", prompt = editText.text.toString(), 0, 256, 1, 0, 0)

            val jsonData = Gson().toJson(modelClass)
            Log.d("Model Class", modelClass.toString())
            Log.d("Json Data", jsonData.toString())

                       val json = JsonObject()
                       json.addProperty("model","code-davinci-002")
                       json.addProperty("prompt","Factorial")
                       json.addProperty("temperature", 0)
                       json.addProperty("max_tokens", 256)
                       json.addProperty("top_p",1)
                       json.addProperty("frequency_penalty",0)
                       json.addProperty("presence_penalty", 0)
                       Log.d("Json data object", json.toString())

            val quoteService =
                RetrofitHelper.getInstance().create(OpenAiInterface:: class.java)
/*
            GlobalScope.launch {
                val result = quoteService. //no need to write page: it will show by compiler
                if (result.body() != null){*/


                    quoteService.postQuery(json,"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")
                .enqueue(object : Callback<OpenAiDataClass> {
                    override fun onResponse(call: Call<OpenAiDataClass>, response: Response<OpenAiDataClass>) {
                        Log.d("URL", response.toString())

                        val responseBody = response.body()!!
                        Log.d("Response body", responseBody.toString())
//                        textView.text = responseBody[0].choices[0].text
                    }

                    override fun onFailure(call: Call<OpenAiDataClass>, t: Throwable) {
                        Log.d("RESPONSE FAIL", t.message.toString())
                    }
                })

        }

    }
}
