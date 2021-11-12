package com.androiddeveloperquiz.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.androiddeveloperquiz.Utils.ApiState
import com.androiddeveloperquiz.ViewModels.EventsViewModel
import com.androiddeveloperquiz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val dictionaryViewModel: EventsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*dictionaryViewModel.getEvents()
        dictionaryViewModel.response.observe(this, {
            when (it?.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Error: " + it.message)

                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE


                    for (mydata in it.data!!) {
                        Log.d(TAG, "SUCCESS   :  data   " + mydata.actor.avatar_url)
                        Log.d(TAG, "SUCCESS   :  data   " + mydata.actor.id)
                        Log.d(TAG, "SUCCESS   :  data   " + mydata.actor.login)
                        Log.d(TAG, "SUCCESS   :  data   " + mydata.actor.url)
                    }
                    Toast.makeText(this, "Data Receive" + it.data, Toast.LENGTH_SHORT).show()
                }
                else -> binding.progressBar.visibility = View.VISIBLE
            }
        })
*/
        dictionaryViewModel.getEvents()
        handleResponse()
    }

    private fun handleResponse() {
        lifecycleScope.launchWhenStarted {
            dictionaryViewModel.apiStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is ApiState.Success -> {

                        Log.d(TAG, "handleResponse: " + it.response.toString())
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }
                        /*    Log.d("main", "handleResponse: ${it.response}")
                            postAdapter.submitList(it.response)*/
                    }
                    is ApiState.Failure -> {
                        Log.d(TAG, "handleResponse: " + it.error)
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }

                    }
                    is ApiState.Empty -> {
                        Log.d(TAG, "handleResponse: Empty")
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}