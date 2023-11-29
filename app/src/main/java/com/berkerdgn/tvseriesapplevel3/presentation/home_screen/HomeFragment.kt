package com.berkerdgn.tvseriesapplevel3.presentation.home_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModelsItem
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModelsItem
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentHomeBinding
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.AllTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.TodayTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.viewmodel.HomeFragmentViewModel
import com.berkerdgn.tvseriesapplevel3.util.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import kotlin.math.abs



class HomeFragment @Inject constructor(
    val todayTvSeriesAdapter : TodayTvSeriesAdapter,
    val allTvSeriesAdapter: AllTvSeriesAdapter
): Fragment() {

    private var _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!

    private lateinit var allTvSeriesViewModel: HomeFragmentViewModel

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var todayTvSeriesList: TodaysTvSeriesModels





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allTvSeriesViewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)

        binding.allTvSeriesRecyclerView.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.allTvSeriesRecyclerView.adapter = allTvSeriesAdapter
        observeLiveDataForAllTvSeries()


        //get today's date
        val simpleData = SimpleDateFormat("yyyy-M-dd")
        val currentDate = simpleData.format(Date())



        //For Today Tv Show
        viewPager2 = binding.todayViewPager2
        handler = Handler(Looper.myLooper()!!)
        observeLiveDataForTodayTvSeries(data = currentDate)
        viewPager2.adapter = todayTvSeriesAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,4000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)


    }

    private fun observeLiveDataForAllTvSeries(){
        allTvSeriesViewModel.allTvSeriesList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{
                    allTvSeriesAdapter.allTvShowList = it.data!!
                }
                Status.ERROR->{
                    println(it.message)
                }
                Status.LOADING->{
                    println("")
                }
            }
        }
    }

    private fun observeLiveDataForTodayTvSeries(data: String){
        allTvSeriesViewModel.getTodayTvSeries(data)
        allTvSeriesViewModel.todayTvSeriesList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{
                    todayTvSeriesAdapter.todayTvSeriesList = it.data!!
                    todayTvSeriesList = it.data!!
                    println(it.data)
                }
                Status.ERROR->{
                    println(it.message)
                }
                Status.LOADING->{
                    println("")
                }
            }
        }
    }


}