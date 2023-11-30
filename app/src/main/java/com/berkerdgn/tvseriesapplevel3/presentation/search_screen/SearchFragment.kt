package com.berkerdgn.tvseriesapplevel3.presentation.search_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentSearchBinding
import com.berkerdgn.tvseriesapplevel3.presentation.search_screen.adapter.SearchTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.search_screen.viewmodel.SearchViewModel
import com.berkerdgn.tvseriesapplevel3.util.Resource
import com.berkerdgn.tvseriesapplevel3.util.Status
import javax.inject.Inject


class SearchFragment @Inject constructor(
    private val searchTvSeriesAdapter: SearchTvSeriesAdapter
): Fragment() {

    private var _binding : FragmentSearchBinding ?= null
    private val binding get() = _binding!!

    private lateinit var searchViewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        binding.searchTvSeriesRecyclerView.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.searchTvSeriesRecyclerView.adapter = searchTvSeriesAdapter

        observeLiveDataForSearchTvShow()

        binding.tvShowSearch.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                searchViewModel.getSearchTvSeries(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                searchViewModel.getSearchTvSeries(p0)
                return true
            }

        })


    }

    private fun observeLiveDataForSearchTvShow(){
        searchViewModel.searchTvSeriesList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS ->{
                    searchTvSeriesAdapter.searchTvSeriesList = it.data!!
                }
                Status.LOADING ->{
                    println(it.status)
                }
                Status.ERROR ->{
                    println(it.message)
                }
            }
        }
    }


}