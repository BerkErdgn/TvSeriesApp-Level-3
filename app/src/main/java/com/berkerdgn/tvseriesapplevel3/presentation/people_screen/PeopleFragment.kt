package com.berkerdgn.tvseriesapplevel3.presentation.people_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentPeopleBinding
import com.berkerdgn.tvseriesapplevel3.presentation.people_screen.view_model.PeopleViewModel
import com.berkerdgn.tvseriesapplevel3.util.Status
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class PeopleFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment() {

    private var _binding : FragmentPeopleBinding ?=null
    private val binding : FragmentPeopleBinding
        get() = _binding!!

    private lateinit var peopleViewModel: PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPeopleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleViewModel = ViewModelProvider(requireActivity()).get(PeopleViewModel::class.java)

        val bundle : PeopleFragmentArgs by navArgs()
        val idPeople = bundle.idPeople
        observeLiveDataForPeople(idPeople)

    }


    private fun observeLiveDataForPeople(idPeople:String){
        peopleViewModel.getPeople(idPeople)
        peopleViewModel.people.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    val people = it.data!!

                    try {
                        glide.load(people.image.medium).into(binding.peopleImageView)
                    }catch (e:Exception) { println(e.localizedMessage) }

                    binding.nameTextView10.text = people.name
                    binding.countryTextView.text = people.country.name
                    binding.birtdayTextView.text = people.birthday
                    binding.genderTextView.text = people.gender
                    try {
                        binding.deadTextView.text = people.deathday.toString()
                    }catch (e:Exception) { println(e.localizedMessage) }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }
    }

}