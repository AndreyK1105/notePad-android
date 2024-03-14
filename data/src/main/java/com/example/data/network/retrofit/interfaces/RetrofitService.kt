package com.example.data.network.retrofit.interfaces

import com.example.data.network.models.CalendarFull
import com.example.data.network.models.Resource
import com.example.data.network.models.SingleUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("1f61fbdf50aae39212cabea5ae65486784547d871f76518f4caa4ef1a0e3b0ff@group.calendar.google.com")
    fun getCalendarList(): Call<CalendarFull>

    @GET("api/users/2")
    fun getSingleUser():Call<SingleUser>

    @GET("api/unknown")
    fun getListResource():Call<Resource>


    companion object {
        var BASE_URL="https://reqres.in/"
        var BASE_URLCALEND="https://www.googleapis.com/calendar/v3/users/me/calendarList/"
        fun create():RetrofitService{
         val retrofit =Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BASE_URLCALEND)
             .build()

            return  retrofit.create(RetrofitService::class.java)
        }

    }
}