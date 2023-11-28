package com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentSecondMainBinding
import com.berkerdgn.tvseriesapplevel3.presentation.home_screen.HomeFragment
import com.berkerdgn.tvseriesapplevel3.presentation.search_screen.SearchFragment
import com.berkerdgn.tvseriesapplevel3.presentation.social_screen.SocialFragment
import com.berkerdgn.tvseriesapplevel3.presentation.user_screen.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class SecondMainFragment : Fragment() {

    private  var _binding : FragmentSecondMainBinding ?= null
    private val binding get() = _binding!!

    private lateinit var navView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondMainBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replace(HomeFragment())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replace(HomeFragment())
                R.id.navigation_search -> replace(SearchFragment())
                R.id.navigation_social -> replace(SocialFragment())
                R.id.navigation_profile -> replace(UserFragment())

            }
            true
        }

    }


    private fun replace(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navHostFrameLayout,fragment)
        fragmentTransaction.commit()

    }

}