package com.example.spiice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spiice.MainActivity
import com.example.spiice.R
import com.example.spiice.SharedPreferences
import com.example.spiice.ViewModel
import com.example.spiice.databinding.SignUpLayputBinding

class SignUpFragment : Fragment () {
    private lateinit var binding: SignUpLayputBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpLayputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.subtitle.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(LogInFragment())
        }

        binding.signUpBt.setOnClickListener {
            binding.emailTIL.error =  if (!viewModel.checkEmail(binding.emailTIET.text.toString()) && !viewModel.checkEmailExists(binding.emailTIET.text.toString()))
                requireContext().getString(R.string.badEmail) else ""
            binding.passwordTIL.error =  if (!viewModel.checkPassword(binding.passwordTIET.text.toString()))
                requireContext().getString(R.string.badPassword) else ""
            binding.firstNameTIL.error =  if (!viewModel.checkName(binding.firstNameTIET.text.toString()))
                requireContext().getString(R.string.badName) else ""
            binding.lastNameTIL.error =  if (!viewModel.checkName(binding.lastNameTIET.text.toString()))
                requireContext().getString(R.string.badName) else ""

            if (binding.emailTIL.error == null && binding.passwordTIL.error == null && binding.firstNameTIL.error == null && binding.lastNameTIL.error == null){
                (activity as? MainActivity)?.addFragment(MainFragment())
                SharedPreferences.setEmailsPasswords(binding.emailTIET.text.toString(), binding.passwordTIET.text.toString())
                SharedPreferences.setLoggedEmail(binding.emailTIET.text.toString())
            }
        }
    }
}