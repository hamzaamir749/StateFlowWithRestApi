package com.androiddeveloperquiz.Repositories


import com.androiddeveloperquiz.Models.EventsModel
import com.androiddeveloperquiz.Network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class EventsRepository(private val apiInterface: ApiInterface) {

    fun getEventsFromGithub(): Flow<Response<MutableList<EventsModel>>> = flow {
        emit(apiInterface.getEventsFromGithub())
    }.flowOn(Dispatchers.IO)

}