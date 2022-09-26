package com.androiddeveloperquiz.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddeveloperquiz.Repositories.EventsRepository
import com.androiddeveloperquiz.Utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: EventsRepository) :
    ViewModel() {
    private val _apiStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val apiStateFlow: StateFlow<ApiState> = _apiStateFlow

    @InternalCoroutinesApi
    fun getEvents() = viewModelScope.launch {
        repository.getEventsFromGithub()
            .onStart {
                _apiStateFlow.value = ApiState.Loading
            }
            .catch { e ->
                _apiStateFlow.value = ApiState.Failure(e)
            }.collect { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        _apiStateFlow.value = ApiState.Success(it)
                    }
                } else {
                    _apiStateFlow.value = ApiState.Error("Something went wrong")
                }
            }
    }
}