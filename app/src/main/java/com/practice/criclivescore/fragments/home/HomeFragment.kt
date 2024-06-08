package com.practice.criclivescore.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.practice.criclivescore.R
import com.practice.criclivescore.api.ApiInterface
import com.practice.criclivescore.api.ApiUtilities
import com.practice.criclivescore.constant.Constant.API_KEY
import com.practice.criclivescore.constant.Constant.SERIES_ID
import com.practice.criclivescore.databinding.FragmentHomeBinding
import com.practice.criclivescore.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val seriesAdapter: SeriesAdapter by lazy {
        SeriesAdapter()
    }
    private var matchApi = ApiUtilities.getRetrofitInstance().create(ApiInterface::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        checkInternet()
    }

    private fun checkInternet() {
        val networkManager = NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it == true) {
                getAllSeries()
                binding.apply {
                    rvSeries.visibility = View.VISIBLE
                    progressBar.visibility = View.VISIBLE
                    mtvNoInternet.visibility = View.GONE
                }
            } else {
                binding.apply {
                    rvSeries.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    mtvNoInternet.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getAllSeries(){
        lifecycleScope.launch(Dispatchers.IO) {
            val result = matchApi.getSeries(API_KEY, SERIES_ID)
            if (result.body() != null) {
                println("API Check, ${result.body()!!.data.matchList}")
                withContext(Dispatchers.Main) {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        mtvNoSeries.visibility = View.GONE
                        rvSeries.adapter = seriesAdapter
                        seriesAdapter.asyncDiffer.submitList(result.body()!!.data.matchList)
                    }
                }
            }
        }
    }
}