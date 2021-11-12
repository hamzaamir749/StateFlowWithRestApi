package com.androiddeveloperquiz.Repositories


import com.androiddeveloperquiz.Models.EventsModel
import com.androiddeveloperquiz.Network.ApiInterface

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
class EventsRepository(private val apiInterface: ApiInterface) {

    fun getEventsFromGithub() : Flow<ArrayList<EventsModel>> = flow {
        emit(apiInterface.getEventsFromGithub())
    }.flowOn(Dispatchers.IO)

}