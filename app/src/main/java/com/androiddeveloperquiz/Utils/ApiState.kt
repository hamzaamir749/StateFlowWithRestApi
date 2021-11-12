package com.androiddeveloperquiz.Utils

import com.androiddeveloperquiz.Models.EventsModel

sealed class ApiState {
    object Loading : ApiState()
    object Empty : ApiState()
    class Success(val response: ArrayList<EventsModel>) : ApiState()
    class Failure(val error: Throwable) : ApiState()
}