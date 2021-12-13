package com.max.kkday.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.max.kkday.R
import com.max.kkday.databinding.FragmentHomeBinding
import com.max.kkday.ext.hideKeyboard

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.homeConstraintLayout.setOnClickListener {
            requireActivity().hideKeyboard()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}