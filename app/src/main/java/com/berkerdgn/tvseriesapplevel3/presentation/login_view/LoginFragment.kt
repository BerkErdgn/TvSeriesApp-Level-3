package com.berkerdgn.tvseriesapplevel3.presentation.login_view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding ?= null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        //To prevent someone from logging in again if they log in.
        val currentUser = auth.currentUser
        if (currentUser != null){
            val actionLoginToHome = LoginFragmentDirections.actionLoginFragmentToSecondMainFragment()
            findNavController().navigate(actionLoginToHome)
        }


        binding.signUpTextView4.setOnClickListener {
            val actionLoginToSignup = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(actionLoginToSignup)
        }

        binding.loginButton.setOnClickListener {
            loginClicked(it)
        }

    }


    fun loginClicked(view: View){
        val email = binding.loginMailInputTextView.text.toString()
        val password = binding.loginPasswordInputText.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Snackbar.make(view,"Enter Email and Password", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.GRAY)
                .setTextColor(Color.WHITE)
                .show()
        }else{
            auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    val actionLoginToHome = LoginFragmentDirections.actionLoginFragmentToSecondMainFragment()
                    findNavController().navigate(actionLoginToHome)
                }.addOnFailureListener {
                    Snackbar.make(view,it.localizedMessage, Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(Color.GRAY)
                        .setTextColor(Color.WHITE)
                        .show()
                }
        }
    }


}