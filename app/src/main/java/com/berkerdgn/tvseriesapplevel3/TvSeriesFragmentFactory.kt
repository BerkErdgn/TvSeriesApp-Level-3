package com.berkerdgn.tvseriesapplevel3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.HomeFragment
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.AllTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.TodayTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen.SecondMainFragment
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class TvSeriesFragmentFactory @Inject constructor(
    val todayTvSeriesAdapter : TodayTvSeriesAdapter,
    val allTvSeriesAdapter: AllTvSeriesAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            HomeFragment::class.java.name -> HomeFragment(todayTvSeriesAdapter,allTvSeriesAdapter)
            SecondMainFragment::class.java.name -> SecondMainFragment(todayTvSeriesAdapter,allTvSeriesAdapter)
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }

}