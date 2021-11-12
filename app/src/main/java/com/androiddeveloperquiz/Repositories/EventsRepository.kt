package com.androiddeveloperquiz.Repositories

import android.util.Log
import com.androiddeveloperquiz.Models.EventsModel
import com.androiddeveloperquiz.Network.ApiInterface
import com.androiddeveloperquiz.Utils.Status
import com.androiddeveloperquiz.Utils.Result
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class EventsRepository(private val apiInterface: ApiInterface) {

    fun getEventsFromGithub() : Flow<ArrayList<EventsModel>> = flow {
        emit(apiInterface.getEventsFromGithub())
    }.flowOn(Dispatchers.IO)

}