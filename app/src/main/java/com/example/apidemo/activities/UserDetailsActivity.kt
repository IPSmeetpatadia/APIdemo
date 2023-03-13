package com.example.apidemo.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.apidemo.R
import com.example.apidemo.dataclasses.dummyEMP.Users
import com.example.apidemo.interfaces.UserDetailsInterface
import kotlinx.android.synthetic.main.activity_user_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val bundle:Bundle? = intent.extras
        val userID = bundle!!.getString("userID")

        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/users/")
            .build()
            .create(UserDetailsInterface::class.java)

        urlBuilder.getDetailsOfUser(userID.toString())
            .enqueue(object : Callback<Users?> {
                override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                    val responseBody = response.body()
                    Log.d("url", response.toString())

                    responseBody!!.apply {
                        Glide.with(this@UserDetailsActivity).load(image).into(empD_img)
                        empD_fullName.text = firstName + " " + maidenName + " " + lastName
                        empD_age.text = age.toString()
                        empD_gender.text = gender
                        empD_dob.text = birthDate
                        empD_bloodGroup.text = bloodGroup
                        empD_email.text = email
                        empD_contactNo.text = phone
                        val addr = StringBuilder()
                        addr.append(address.address+", \n"+address.city+", \n"+address.state+"-"+address.postalCode)
                        empD_address.text = addr
                        empD_cname.text = company.name
                        empD_department.text = company.department
                        empD_title.text = company.title
                    }
                }

                override fun onFailure(call: Call<Users?>, t: Throwable) {
                    Log.d("getDetailsOfUser() ~ response fail", t.message.toString())
                }
            })
    }
}