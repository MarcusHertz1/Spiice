package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spiice.MainActivity
import com.example.spiice.ViewModel
import com.example.spiice.adapter.Adapter
import com.example.spiice.databinding.MainLayoutBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainLayoutBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewBt.setOnClickListener {
            (activity as? MainActivity)?.addFragment(NewNoteFragment())
        }

        binding.logoutBt.setOnClickListener {
            (activity as? MainActivity)?.logOut()
        }

        viewModel.updateRecyclerViewLiveData.observe(viewLifecycleOwner){
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = Adapter(viewModel.getDBArray(requireContext())
            ) { id ->
                viewModel.deletedItemId = id
                (activity as? MainActivity)?.addFragment(DeleteDialogFragment())
            }
            binding.recyclerView.adapter = adapter
        }
        viewModel.updateRecyclerView()
    }
}