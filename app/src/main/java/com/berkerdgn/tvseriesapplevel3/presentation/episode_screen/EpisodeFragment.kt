package com.berkerdgn.tvseriesapplevel3.presentation.episode_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.berkerdgn.tvseriesapplevel3.data.remote.model.episodesModels.EpisodesModel
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentEpisodeBinding
import com.berkerdgn.tvseriesapplevel3.presentation.episode_screen.view_model.EpisodeViewModel
import com.berkerdgn.tvseriesapplevel3.util.Status
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class EpisodeFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment() {

    private var _binding : FragmentEpisodeBinding ?= null
    private val binding get() = _binding!!

    private lateinit var episodeViewModel : EpisodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeViewModel = ViewModelProvider(requireActivity()).get(EpisodeViewModel::class.java)

        val bundle : EpisodeFragmentArgs by navArgs()
        val idEpisode = bundle.idEpisode

        observeLiveDataForEpisode(idEpisode)



    }

    private fun observeLiveDataForEpisode(idEpisode:String){
        episodeViewModel.getEpisode(idEpisode)
        episodeViewModel.episode.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar2.visibility = View.GONE
                    val episode = it.data!!
                    try {
                        glide.load(episode.image.original).into(binding.imageView3)
                    }catch (e:Exception){}
                    binding.episodeNameTextView.text = episode.name
                    binding.seasonTextView.text = episode.season.toString()
                    binding.ratingTextView.text = episode.rating.average.toString()
                    binding.dateTextView.text = episode.airdate
                    binding.timeTextView.text = episode.airtime
                    binding.runtimeTextView16.text = episode.runtime.toString()
                    binding.summaryTextView.text = episode.summary
                }
                Status.ERROR -> {
                    binding.progressBar2.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar2.visibility = View.VISIBLE
                }
            }
        }



    }

}