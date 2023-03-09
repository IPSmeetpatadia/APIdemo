package com.example.apidemo.activities

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.apidemo.R
import com.example.apidemo.dataclasses.TranslationDataClass
import com.example.apidemo.interfaces.TranslationInterface
import kotlinx.android.synthetic.main.activity_translation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TranslationActivity : AppCompatActivity() {

    private lateinit var fromLanguage: EditText
    lateinit var toLanguage: EditText

    private lateinit var select_fromLanguage: Spinner
    private lateinit var select_toLanguage: Spinner
    lateinit var translateFromLanguage: String
    lateinit var translateToLanguage: String

    private val languages = mapOf(
        "Afrikaans" to "af",
        "Amharic" to "am",
        "Arabic" to "ar",
        "Aragonese" to "an",
        "Armenian" to "hy",
        "Avaric" to "av",
        "Avestan" to "ae",
        "Azerbaijani" to "az",
        "Basque" to "eu",
        "Burmese" to "my",
        "Belarusian" to "be",
        "Bengali" to "bn",
        "Bosnian" to "bs",
        "Bulgarian" to "bg",
        "Catalan" to "ca",
        "Chamorro" to "ch",
        "Chechen" to "ce",
        "Chichewa" to "ny",
        "Chinese" to "zh",
        "Chinese Taiwan" to "zh-tw",
        "Chinese PRC" to "zh-cn",
        "Chinese Hong Kong" to "zh-hk",
        "Chinese Singapore" to "zh-sg",
        "English" to "en",
        "Hindi" to "hi"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        fromLanguage = findViewById(R.id.edtxt_from)
        toLanguage = findViewById(R.id.edtxt_to)
        select_fromLanguage = findViewById(R.id.spinner_fromLanguage)
        select_toLanguage = findViewById(R.id.spinner_toLanguage)

        select_toLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedKey = parent?.getItemAtPosition(position) as String
                translateToLanguage = languages[selectedKey].toString()
                txt_toLanguage.text = selectedKey
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        select_fromLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedKey = parent?.getItemAtPosition(position) as String
                translateFromLanguage = languages[selectedKey].toString()
                txt_fromLanguage.text = selectedKey
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages.keys.toList())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked)
        select_fromLanguage.adapter = spinnerAdapter
        select_fromLanguage.setSelection(languages.keys.indexOf("English"))

        select_toLanguage.adapter = spinnerAdapter
        select_toLanguage.setSelection(languages.keys.indexOf("Hindi"))

        findViewById<Button>(R.id.btn_translate).setOnClickListener {
            getTranslation()
        }
    }

    private fun getTranslation() {
        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://translo.p.rapidapi.com/api/v3/")
            .build()
            .create(TranslationInterface::class.java)

        urlBuilder.getTranslatedText(to = translateToLanguage, text = fromLanguage.text.toString(), from = translateFromLanguage, "83e0bb9359mshf23a5e69a002769p129d96jsn8ed0c455bfcf", "translo.p.rapidapi.com")
            .enqueue(object : Callback<TranslationDataClass?> {
                override fun onResponse(call: Call<TranslationDataClass?>, response: Response<TranslationDataClass?>) {
                    val responseBody = response.body()
                    toLanguage.text = Editable.Factory.getInstance().newEditable(responseBody?.translated_text)
                }

                override fun onFailure(call: Call<TranslationDataClass?>, t: Throwable) {
                    Log.d("getTranslation()", "Response Fail")
                }
            })
    }

}