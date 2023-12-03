package com.berkerdgn.tvseriesapplevel3.presentation.information_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CastAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CrewAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.SeasonsAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.viewmodel.OneTvSeriesViewModel
import com.berkerdgn.tvseriesapplevel3.util.Status
import javax.inject.Inject

class OneTvSeriesFragment @Inject constructor(
    private val castAdapter: CastAdapter,
    private val crewAdapter: CrewAdapter,
    private val seasonsAdapter: SeasonsAdapter
) : Fragment() {

    private lateinit var oneTvSeriesViewModel: OneTvSeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_tv_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oneTvSeriesViewModel = ViewModelProvider(requireActivity()).get(OneTvSeriesViewModel::class.java)

    }

    private fun observeLiveDataForOneTvSeriesModel(idTvSeries: String){
        oneTvSeriesViewModel.getOneTvSeries(idTvSeries)
        oneTvSeriesViewModel.onTvSeries.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS->{

                }
                Status.LOADING->{

                }
                Status.ERROR->{

                }
            }
        }
    }

    private fun observeLiveDataTvShowEpisodes(idTvSeries:String){
        oneTvSeriesViewModel.getTvShowEpisodes(idTvSeries)
        oneTvSeriesViewModel.onTvShowEpisodesList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS ->{

                }
                Status.LOADING->{

                }
                Status.ERROR->{

                }
            }
        }
    }

    private fun observeLiveDataTvShowCrews(idTvSeries:String){
        oneTvSeriesViewModel.getTvShowCrews(idTvSeries)
        oneTvSeriesViewModel.oneTvSeriesCrewList.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{

                }
                Status.LOADING->{

                }
                Status.ERROR->{

                }
            }
        }
    }




}