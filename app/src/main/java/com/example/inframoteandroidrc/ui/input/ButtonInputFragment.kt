package com.example.inframoteandroidrc.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inframoteandroidrc.R

class ButtonInputFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_button_input, container, false)

        val spinner: Spinner = v.findViewById(R.id.protocol_selector)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ir_protocols,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val navController = findNavController()
        v.findViewById<Button>(R.id.submit_button).setOnClickListener {
            navController.navigate(R.id.action_buttonInputFragment_to_nav_home)
        }

        return v.rootView
    }


}
