package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class Fragment_a : Fragment(R.layout.fragment_a){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        val buttonFragA = view.findViewById<Button>(R.id.Frag_A_Btn)
        buttonFragA.setOnClickListener {
            goToFragmentB()
        }

        return view
    }

    private fun goToFragmentB() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerFrag_1, Fragment_b())
        fragmentTransaction.commit()
    }
}