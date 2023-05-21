package com.example.spiice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.spiice.databinding.ActivityMainBinding
import com.example.spiice.fragments.LogInFragment
import com.example.spiice.fragments.MainFragment
import com.example.spiice.fragments.StartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPreferences.init (this)

        if (SharedPreferences.getLoggedEmail() == "") {
            addFragment(StartFragment())
        } else addFragment(MainFragment())
    }

    fun addFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.fragmentContainerView.id, fragment, fragment.toString())
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.commit()
    }

    fun logOut(){
        SharedPreferences.setLoggedEmail("")
        supportFragmentManager.popBackStack()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainerView.id, LogInFragment(), LogInFragment().toString())
        fragmentTransaction.addToBackStack(LogInFragment().toString())
        fragmentTransaction.commit()
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainerView.id, fragment, fragment.toString())
        fragmentTransaction.commit()
    }
}