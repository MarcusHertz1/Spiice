package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.MainActivity
import com.example.spiice.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.subtitle.setOnClickListener {
            (activity as? MainActivity)?.addFragment(SignUpFragment())
        }

        binding.logInBt.setOnClickListener {
            binding.emailTIL.error =  if (binding.emailTIET.text?.isEmpty() == true ) requireContext().getString(R.string.obligatoryField) else ""
            binding.passwordTIL.error =  if (binding.passwordTIET.text?.isEmpty() == true ) requireContext().getString(R.string.obligatoryField) else ""
        }
    }
}