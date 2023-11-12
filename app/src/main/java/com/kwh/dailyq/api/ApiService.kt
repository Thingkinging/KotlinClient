package com.kwh.dailyq.api

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kwh.dailyq.api.converter.LocalDateConverterFactory
import com.kwh.dailyq.api.response.Question
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.time.LocalDate

interface ApiService {

    companion object {
        private var INSTANCE: ApiService? = null

        private fun create(context: Context) : ApiService{
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(LocalDateConverterFactory())
                .baseUrl("http://192.168.0.12:5000")
                .build()
                .create(ApiService::class.java)
        }

        fun init(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: create(context).also {
                INSTANCE = it
            }
        }

        fun getInstance(): ApiService = INSTANCE!!
    }

    @GET("/v1/questions/{qid}")
    suspend fun getQuestion(@Path("qid") qid: LocalDate): Question
}