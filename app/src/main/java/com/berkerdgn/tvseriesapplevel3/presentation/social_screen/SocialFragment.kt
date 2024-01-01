package com.berkerdgn.tvseriesapplevel3.presentation.social_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentSocialBinding
import com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen.SecondMainFragmentDirections
import com.berkerdgn.tvseriesapplevel3.presentation.social_screen.adapter.PostAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.social_screen.viewmodel.SocialViewModel
import javax.inject.Inject


class SocialFragment : Fragment() {

    private var _binding : FragmentSocialBinding ?=null
    private val binding get() = _binding!!

    private lateinit var socialViewModel: SocialViewModel

    private val postAdapter = PostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSocialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        socialViewModel = ViewModelProvider(requireActivity()).get(SocialViewModel::class.java)

        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.commentRecyclerView.adapter = postAdapter

        binding.AddfloatingActionButton.setOnClickListener {
            val action = SecondMainFragmentDirections.actionSecondMainFragmentToAddPostScreenFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeSocialLiveData()
    }

    private fun observeSocialLiveData(){
        socialViewModel.postsList.observe(viewLifecycleOwner){
           postAdapter.postsList = it
        }
    }

}