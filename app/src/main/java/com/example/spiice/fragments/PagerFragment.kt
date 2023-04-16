package com.example.spiice.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.R
import com.example.spiice.databinding.PagerFragmentLayoutBinding

class PagerFragment : Fragment() {
    private lateinit var binding: PagerFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PagerFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePager()
    }

    private fun changePager() {
        val imageArray = arrayOf(
            R.drawable.pager_image_0,
            R.drawable.pager_image_1,
            R.drawable.pager_image_2,
            R.drawable.pager_image_3,
            R.drawable.pager_image_4
        )
        val textArray = arrayOf(
            R.string.pager_text_0,
            R.string.pager_text_1,
            R.string.pager_text_2,
            R.string.pager_text_3,
            R.string.pager_text_4,
        )
        with(binding)
        {
            val dotArray = arrayOf(
                dot0,
                dot1,
                dot2,
                dot3,
                dot4
            )

        val mainHandler = Handler(Looper.getMainLooper())
        var activePage = 0

            mainHandler.post(object : Runnable {
                override fun run() {
                    pagerImage.setImageResource(imageArray[activePage])
                    subtitle.text = requireContext().getString(textArray[activePage])
                    for (i in 0..4) {
                        dotArray[i].setImageResource(
                            if (i == activePage) R.drawable.active_dot
                            else R.drawable.inactive_dot)
                    }
                    activePage++
                    if (activePage == 5) activePage = 0
                    mainHandler.postDelayed(this, 3000)
                }
            })
        }
    }
}