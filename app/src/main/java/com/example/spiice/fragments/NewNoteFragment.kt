package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spiice.R
import com.example.spiice.ViewModel
import com.example.spiice.databinding.NewNoteLayoutBinding


class NewNoteFragment : Fragment() {
    private lateinit var binding: NewNoteLayoutBinding
    private val viewModel: ViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewNoteLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBt.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.addBt.setOnClickListener {
            binding.messageTIL.error =
                if (binding.messageTIET.text?.isEmpty() == true) requireContext().getString(
                    R.string.obligatoryField
                ) else ""
            if (binding.messageTIL.error == null) {
                viewModel.setDBDate(requireContext(), binding.titleTIET.text.toString(), binding.messageTIET.text.toString())
                activity?.onBackPressedDispatcher?.onBackPressed()
                viewModel.updateRecyclerView()
            }
        }
    }
}