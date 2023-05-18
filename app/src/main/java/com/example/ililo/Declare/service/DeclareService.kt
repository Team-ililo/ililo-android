package com.example.ililo.Declare.service

import android.util.Log
import com.example.ililo.ApplicationClass.Companion.sRetrofit
import com.example.ililo.Declare.model.DeclareReq
import com.example.ililo.Declare.model.DeclareRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeclareService(val declareListInterface: DeclareInterface) {
    private val retrofit: DeclareRetrofit = sRetrofit.create(DeclareRetrofit::class.java)

    fun tryPostDeclare(car_num: String, reason: String) {
        retrofit.postDeclareRes(DeclareReq(car_num,reason)).enqueue((object : Callback<DeclareRes>{
            override fun onResponse(call: Call<DeclareRes>, response: Response<DeclareRes>) {
                if (response.isSuccessful) {
                    declareListInterface.onGetDeclareListSuccess(response.body() as DeclareRes)
                    Log.d("tryPostDeclare", "success")
                } else {
                    Log.d("tryPostDeclare", "failure")
                }
            }
            override fun onFailure(call: Call<DeclareRes>, t: Throwable) {
                Log.d("tryPostDeclare", t.message!!)
                t.printStackTrace()
                declareListInterface.onGetDeclareListFailure(t.message ?: "통신오류")            }

        }))
    }
}