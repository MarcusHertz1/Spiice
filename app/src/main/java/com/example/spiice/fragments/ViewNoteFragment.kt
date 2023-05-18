package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spiice.ViewModel
import com.example.spiice.databinding.ViewNoteLayoutBinding

class ViewNoteFragment : Fragment() {
    private lateinit var binding: ViewNoteLayoutBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewNoteLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewNoteGrayContainer.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.viewNoteContainer.setOnClickListener {}

        binding.titleTV.text = viewModel.showingTitle
        binding.messageTV.text = viewModel.showingMessage
    }
}