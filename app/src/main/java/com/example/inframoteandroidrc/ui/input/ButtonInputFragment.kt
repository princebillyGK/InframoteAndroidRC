package com.example.inframoteandroidrc.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inframoteandroidrc.R
import com.example.inframoteandroidrc.database.RemoteButton
import com.example.inframoteandroidrc.database.RemoteDatabase
import com.example.inframoteandroidrc.databinding.FragmentButtonInputBinding
import com.example.inframoteandroidrc.ui.home.HomeViewModel
import com.example.inframoteandroidrc.ui.home.HomeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ButtonInputFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentButtonInputBinding>(
            inflater, R.layout.fragment_button_input, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = RemoteDatabase.getInstance(application).remoteButtonDao
        val viewModelFactory = ButtonInputViewModelFactory(dataSource, application)

        val buttonViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ButtonInputViewModel::class.java)

        val homeViewModel =
            ViewModelProvider(
                this, HomeViewModelFactory(dataSource, application)
            ).get(HomeViewModel::class.java)

        homeViewModel.updateButtons()


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ir_protocols,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.protocolSelector.adapter = adapter
        }


        val navController = findNavController()
        binding.submitButton.setOnClickListener {
            navController.navigate(R.id.action_buttonInputFragment_to_nav_home)
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    buttonViewModel.saveButton(
                        RemoteButton(
                            name = binding.editName.text.toString(),
                            address = binding.editAddress.text.toString(),
                            protocol = binding.protocolSelector.selectedItem.toString(),
                            command = binding.editCommand.text.toString()
                        )
                    )
                }
            } catch (err: Exception) {
                Toast.makeText(requireContext(), err.message, Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}
