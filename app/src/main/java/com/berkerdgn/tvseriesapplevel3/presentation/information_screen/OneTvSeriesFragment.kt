package com.berkerdgn.tvseriesapplevel3.presentation.information_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.data.remote.model.tvSeriesModels.TvSeriesModels
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentOneTvSeriesBinding
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CastAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.CrewAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.adapter.SeasonsAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.information_screen.viewmodel.OneTvSeriesViewModel
import com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen.SecondMainFragmentDirections
import com.berkerdgn.tvseriesapplevel3.util.Status
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class OneTvSeriesFragment @Inject constructor(
    private val castAdapter: CastAdapter,
    private val crewAdapter: CrewAdapter,
    private val seasonsAdapter: SeasonsAdapter,
    private val glide: RequestManager
) : Fragment() {

    private var _binding : FragmentOneTvSeriesBinding ?= null
    private val binding get() = _binding!!

    private lateinit var oneTvSeriesViewModel: OneTvSeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOneTvSeriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: OneTvSeriesFragmentArgs by navArgs()
        val idTvSeries = bundle.idTvSeries

        oneTvSeriesViewModel = ViewModelProvider(requireActivity()).get(OneTvSeriesViewModel::class.java)


        binding.seasonsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.seasonsRecyclerView.adapter = seasonsAdapter

        binding.castRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.castRecyclerView.adapter = castAdapter

        binding.crewRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.crewRecyclerView.adapter = crewAdapter


        observeLiveDataForOneTvSeriesModel(idTvSeries)
        observeLiveDataTvShowCrews(idTvSeries)
        observeLiveDataTvShowEpisodes(idTvSeries)

    }

    private fun observeLiveDataForOneTvSeriesModel(idTvSeries: String){
        oneTvSeriesViewModel.getOneTvSeries(idTvSeries)
        oneTvSeriesViewModel.onTvSeries.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS->{
                    placeAllInformationPut(it.data!!)
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
                    seasonsAdapter.seasonsList= it.data!!
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
                    crewAdapter.crewList= it.data!!
                }
                Status.LOADING->{

                }
                Status.ERROR->{

                }
            }
        }
    }

    private fun placeAllInformationPut(tvSeriesModel: TvSeriesModels){
        castAdapter.castList = tvSeriesModel._embedded.cast

        glide.load(tvSeriesModel.image.medium).into(binding.tvShowImageView)
        binding.oneTvSeriesNameTextView.text = tvSeriesModel.name
        binding.conceptTextView11.text = tvSeriesModel.summary
        binding.imdbTextView13.text = tvSeriesModel.rating.average.toString()
        binding.genresTextView13.text = tvSeriesModel.genres.joinToString(",")
        binding.statusTextView.text = tvSeriesModel.status
        binding.runtimeTextView.text = tvSeriesModel.runtime.toString()
        binding.scheduleTextView.text = "${tvSeriesModel.schedule.time} + ${tvSeriesModel.schedule.days.joinToString(",")}"
        binding.premieredTextView.text = tvSeriesModel.premiered
        binding.endedTextView.text = tvSeriesModel.ended
        binding.languageTextView.text = tvSeriesModel.language

    }


}