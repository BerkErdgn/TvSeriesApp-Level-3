package com.berkerdgn.tvseriesapplevel3.presentation.splash_screen_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentOnBoardingBinding
import com.bumptech.glide.request.transition.Transition.ViewAdapter


class OnBoardingFragment : Fragment() {

    private var _binding : FragmentOnBoardingBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val splashFragmentList = arrayListOf<Fragment>(
            FistSplashFragment(),
            SecondSplashFragment()
        )

        val adapter = OnBoardingViewPagerAdapter(splashFragmentList, requireActivity().supportFragmentManager,lifecycle)

        val viewPager = binding.onBoardingViewPager
        viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(viewPager)

    }


}