package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.MainActivity
import com.example.spiice.R
import com.example.spiice.SharedPreferences
import com.example.spiice.ViewModel
import com.example.spiice.databinding.LogInLayoutBinding

class LogInFragment : Fragment() {
    private lateinit var binding: LogInLayoutBinding
    private val viewModel: ViewModel by viewModels()

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
            (activity as? MainActivity)?.replaceFragment(SignUpFragment())
        }

        binding.logInBt.setOnClickListener {
            binding.emailTIL.error =  if (binding.emailTIET.text?.isEmpty() == true ) requireContext().getString(R.string.obligatoryField) else ""
            binding.passwordTIL.error =  if (binding.passwordTIET.text?.isEmpty() == true ) requireContext().getString(R.string.obligatoryField) else ""
            if (binding.emailTIL.error == null && binding.passwordTIL.error == null){
                when (viewModel.checkEmailPassword(binding.emailTIET.text.toString(), binding.passwordTIET.text.toString())) {
                    ViewModel.NO_EMAIL -> binding.emailTIL.error = ViewModel.NO_EMAIL
                    ViewModel.BAD_PASSWORD -> binding.passwordTIL.error = ViewModel.BAD_PASSWORD
                    ViewModel.CORRECT -> {
                        (activity as? MainActivity)?.apply {
                            addFragment(MainFragment())
                            SharedPreferences.setLoggedEmail(binding.emailTIET.text.toString()) }
                    }
                }
            }
        }
    }
}