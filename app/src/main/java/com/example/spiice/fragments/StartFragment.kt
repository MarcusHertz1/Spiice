package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.MainActivity
import com.example.spiice.databinding.StartFragmentLayoutBinding

class StartFragment : Fragment() {
    private lateinit var binding: StartFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.discoverThePlatformBt.setOnClickListener {
            (activity as? MainActivity)?.addFragment(PagerFragment())
        }
    }
}