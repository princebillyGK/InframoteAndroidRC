package com.example.inframoteandroidrc.ui.home

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var irManager: ConsumerIrManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        try {
            irManager =
                requireContext().getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
            if (irManager.hasIrEmitter()) {
                Toast.makeText(requireContext(), "This device have IR Blaster", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "This device doesn't have any IR blaster",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (err: Exception) {
            Toast.makeText(requireContext(), err.message, Toast.LENGTH_LONG).show()

        }

        val application = requireNotNull(this.activity).application

        val dataSource = RemoteDatabase.getInstance(application).remoteButtonDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProvider(
            this, viewModelFactory
        )[HomeViewModel::class.java]


        val adapter = RemoteButtonAdapter({ handleDelete(it) }, { handleSendAction() })
        homeViewModel.buttons.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.buttonsList.adapter = adapter

        binding.addNewButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_buttonInputFragment)
        }
        return binding.root
    }

    private fun handleDelete(button: RemoteButton) {
        homeViewModel.delete(button)
        Toast.makeText(requireContext(), "Button Deleted", Toast.LENGTH_SHORT).show()
    }

    private fun handleSendAction() {
        try {
            irManager.transmit(57437537, intArrayOf(1000000, 1000000))
            Toast.makeText(requireContext(), "IR command send", Toast.LENGTH_SHORT).show()
        } catch (err: Exception) {
            Toast.makeText(requireContext(), err.message, Toast.LENGTH_LONG).show()
        }
    }

}

