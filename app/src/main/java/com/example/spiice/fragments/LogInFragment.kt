package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.LogInLayoutBinding

class LogInFragment : Fragment() {
    private lateinit var binding: LogInLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LogInLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

}