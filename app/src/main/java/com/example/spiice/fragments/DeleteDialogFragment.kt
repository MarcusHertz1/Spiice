package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spiice.DBHelper
import com.example.spiice.ViewModel
import com.example.spiice.databinding.DeleteDialogLayoutBinding

class DeleteDialogFragment : Fragment() {
    private lateinit var binding: DeleteDialogLayoutBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DeleteDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancel.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.container.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.dialogContainer.setOnClickListener {}

        binding.delete.setOnClickListener {
            val dbHelper = DBHelper(context)
            dbHelper.delete(viewModel.deletedItemId)
            viewModel.updateRecyclerView()
            activity?.onBackPressedDispatcher?.onBackPressed()
        }



    }

}