package com.berkerdgn.tvseriesapplevel3.presentation.splash_screen_view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.berkerdgn.tvseriesapplevel3.R

class LogoSplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingIsFinished()){
                findNavController().navigate(R.id.action_logoSplashFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_logoSplashFragment_to_onBoardingFragment)
            }
        },3000)
        return inflater.inflate(R.layout.fragment_logo_splash, container, false)
    }


    private fun onBoardingIsFinished():Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished",false)
    }
}