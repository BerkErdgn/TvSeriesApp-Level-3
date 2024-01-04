package com.berkerdgn.tvseriesapplevel3.presentation.user_screen

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.tvseriesapplevel3.databinding.FragmentUserBinding
import com.berkerdgn.tvseriesapplevel3.presentation.second_main_screen.SecondMainFragmentDirections
import com.berkerdgn.tvseriesapplevel3.presentation.user_screen.adapter.PersonalPostAdapter
import com.berkerdgn.tvseriesapplevel3.presentation.user_screen.viewmodel.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding ?= null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        val personalPostAdapter = PersonalPostAdapter(userViewModel)

        binding.postRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.postRecyclerView.adapter = personalPostAdapter

        auth = Firebase.auth
        val currentUser = auth.currentUser!!.email!!


        binding.userMailTextView.text = currentUser

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            val action = SecondMainFragmentDirections.actionSecondMainFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeUserSocialLiveData(currentUser,personalPostAdapter)



    }

    private fun observeUserSocialLiveData(id: String, personalPostAdapter: PersonalPostAdapter){
        userViewModel.getPersonalPost(id)
        userViewModel.personalPostList.observe(viewLifecycleOwner){
            personalPostAdapter.personalPostList = it

            binding.socialTextView.text = "${it.size} | Posts"
        }
    }


}