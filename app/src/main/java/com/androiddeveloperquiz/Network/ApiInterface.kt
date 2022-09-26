package com.androiddeveloperquiz.Network


import com.androiddeveloperquiz.Models.EventsModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("events")
    suspend fun getEventsFromGithub(
    ): Response<MutableList<EventsModel>>


}
