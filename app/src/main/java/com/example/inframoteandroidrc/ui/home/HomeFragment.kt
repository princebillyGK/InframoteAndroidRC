package com.example.inframoteandroidrc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inframoteandroidrc.R
import com.example.inframoteandroidrc.RemoteButtonAdapter
import com.example.inframoteandroidrc.database.RemoteButton
import com.example.inframoteandroidrc.database.RemoteDatabase
import com.example.inframoteandroidrc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel

//    val manager =
//        requireContext().getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = RemoteDatabase.getInstance(application).remoteButtonDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(HomeViewModel::class.java)


        val adapter = RemoteButtonAdapter { handleDelete(it) }
        homeViewModel.buttons.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.buttonsList.adapter = adapter

        binding.addNewButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_buttonInputFragment)
        }
        return binding.root
    }

    fun handleDelete(button: RemoteButton) {
        homeViewModel.delete(button)
    }
}

