package com.berkerdgn.tvseriesapplevel3.presentation.signup_view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding ?= null
    private val binding get() = _binding!!

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.signupButton.setOnClickListener {
            signupClicked(view)
        }

    }

    fun signupClicked(view: View){
        val email = binding.signupMailInputTextView.text.toString()
        val password = binding.signupPasswordInputText.text.toString()
        val verifyPassword = binding.verifySignupPasswordInputText.text.toString()

        if (email.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()){
            Snackbar.make(view,"Enter Email, Password and Verify Password ",Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.GRAY)
                .setTextColor(Color.WHITE)
                .show()
        }else{
            if (password==verifyPassword){
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        val action = SignupFragmentDirections.actionSignupFragmentToSecondMainFragment()
                        findNavController().navigate(action)
                    }.addOnFailureListener {
                        Snackbar.make(view,it.localizedMessage,Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.GRAY)
                            .setTextColor(Color.WHITE)
                            .show()
                    }

            }else{
                Snackbar.make(view,"Your password and verify password are not the same",Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GRAY)
                    .setTextColor(Color.WHITE)
                    .show()
            }
        }
  
    }

    
}