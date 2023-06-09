package com.example.ililo.Notice.service

import android.app.Application
import android.util.Log
import com.example.ililo.ApplicationClass
import com.example.ililo.Notice.model.NoticeRes
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeService(val noticeListInterface: NoticeListInterface) {
    private val retrofit: NoticeRetrofit = ApplicationClass.sRetrofit.create(NoticeRetrofit::class.java)

    fun tryGetNoticeList(apartmentName: String){
        retrofit.getNoticeRes(apartmentName).enqueue(object : Callback<NoticeRes>{
            override fun onResponse(call: Call<NoticeRes>, response: Response<NoticeRes>) {
                if (response.isSuccessful) {
                    noticeListInterface.onGetNoticeListSuccess(response.body() as NoticeRes)
                    Log.d("getNotice", "success")
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = try {
                        val errorJson = JSONObject(errorResponse)
                        errorJson.getString("message")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        null
                    }
                    noticeListInterface.onGetNoticeListFailure(errorMessage ?: "통신오류")
                }
            }
            override fun onFailure(call: Call<NoticeRes>, t: Throwable) {
                Log.d("tryGetNoticeList", t.message!!)
                t.printStackTrace()
                noticeListInterface.onGetNoticeListFailure(t.message ?: "통신오류")
            }
        })
    }
}