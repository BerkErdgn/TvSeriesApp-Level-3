package com.berkerdgn.tvseriesapplevel3.presentation.add_post_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.berkerdgn.tvseriesapplevel3.R
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentAddPostScreenBinding
import com.berkerdgn.tvseriesapplevel3.presentation.add_post_screen.viewmodel.AddPostViewModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class AddPostScreenFragment : Fragment() {

    private var _binding : FragmentAddPostScreenBinding ?= null
    private val binding get() = _binding!!

    private lateinit var auth : FirebaseAuth
    private lateinit var addPostViewModel: AddPostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddPostScreenBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = Firebase.auth

        addPostViewModel = ViewModelProvider(requireActivity()).get(AddPostViewModel::class.java)

        binding.addButton.setOnClickListener {
            uploadPost(it)
        }


    }

    private fun uploadPost(view:View){

        if (auth.currentUser !=null){
            addPostViewModel.uploadPosts(
                comment = binding.commentInputTextView3.text.toString(),
                userEmail = auth.currentUser!!.email!!,
                tvSeriesName = binding.tvSeriesNameInputTextView.text.toString(),
                date = Timestamp.now().toString()
                )
            val action = AddPostScreenFragmentDirections.actionAddPostScreenFragmentToSecondMainFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }




}