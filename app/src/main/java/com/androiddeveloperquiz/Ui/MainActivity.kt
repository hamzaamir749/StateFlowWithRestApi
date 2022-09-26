package com.androiddeveloperquiz.Ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddeveloperquiz.Adapters.EventsAdapter
import com.androiddeveloperquiz.Utils.ApiState
import com.androiddeveloperquiz.ViewModels.EventsViewModel
import com.androiddeveloperquiz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val eventViewModel: EventsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: EventsAdapter

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun handleResponse() {
        lifecycleScope.launchWhenStarted {
            eventViewModel.apiStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is ApiState.Success -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                        }
                        runOnUiThread {
                            adapter.submitList(it.response)
                        }
                    }
                    is ApiState.Failure -> {
                        Log.d(TAG, "handleResponse: " + it.error)
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }
                        Toast.makeText(this@MainActivity, "Error: ${it.error}", Toast.LENGTH_SHORT)
                            .show()

                    }
                    is ApiState.Error -> {

                        binding.apply {
                            progressBar.visibility = View.GONE
                        }
                        Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT)
                            .show()

                    }
                    is ApiState.Empty -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    @InternalCoroutinesApi
    private fun initViews() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }


        eventViewModel.getEvents()
        handleResponse()
    }
}
