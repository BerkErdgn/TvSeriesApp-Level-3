package com.berkerdgn.tvseriesapplevel3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.berkerdgn.tvseriesapplevel3.presentation.episode_screen.EpisodeFragment
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.HomeFragment
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.AllTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.adapter.TodayTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.OneTvSeriesFragment
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CastAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CrewAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.SeasonsAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.people_screen.PeopleFragment
import com.berkerdgn.tvseriesapplevel3.presentation.search_screen.adapter.SearchTvSeriesAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen.SecondMainFragment
import com.berkerdgn.tvseriesapplevel3.presentation.social_screen.SocialFragment
import com.berkerdgn.tvseriesapplevel3.presentation.social_screen.adapter.PostAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class TvSeriesFragmentFactory @Inject constructor(
    private val todayTvSeriesAdapter : TodayTvSeriesAdapter,
    private val allTvSeriesAdapter: AllTvSeriesAdapter,
    private val searchTvSeriesAdapter: SearchTvSeriesAdapter,

    private val castAdapter: CastAdapter,
    private val crewAdapter: CrewAdapter,
    private val seasonsAdapter: SeasonsAdapter,
    private val glide: RequestManager

): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            HomeFragment::class.java.name -> HomeFragment(todayTvSeriesAdapter,allTvSeriesAdapter)
            SecondMainFragment::class.java.name -> SecondMainFragment(todayTvSeriesAdapter,allTvSeriesAdapter,searchTvSeriesAdapter)
            OneTvSeriesFragment::class.java.name -> OneTvSeriesFragment(castAdapter, crewAdapter, seasonsAdapter, glide)
            PeopleFragment::class.java.name -> PeopleFragment(glide)
            EpisodeFragment::class.java.name -> EpisodeFragment(glide)
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }

}