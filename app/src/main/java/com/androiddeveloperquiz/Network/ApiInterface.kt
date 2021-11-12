package com.androiddeveloperquiz.Network


import com.androiddeveloperquiz.Models.EventsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface ApiInterface {

    @GET("events")
    suspend fun getEventsFromGithub(
    ): ArrayList<EventsModel>


}
