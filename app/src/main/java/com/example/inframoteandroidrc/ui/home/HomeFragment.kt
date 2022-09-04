package com.example.inframoteandroidrc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inframoteandroidrc.R

class HomeFragment : Fragment() {

//    val manager =
//        requireContext().getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_home, container, false).rootView
        v.findViewById<Button>(R.id.add_new_button).setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_buttonInputFragment)
        }
        return v.rootView
    }
}
