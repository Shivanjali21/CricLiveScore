package com.practice.criclivescore.fragments.splash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.practice.criclivescore.R
import com.practice.criclivescore.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var binding : FragmentSplashBinding? = null
    private var splashTimer = 6000 // Splash screen timer in milliseconds
    private val mTimeCounter = object : CountDownTimer(splashTimer.toLong(), 100) {
        override fun onTick(p0: Long) {
            // Not used in this example
        }
        override fun onFinish() {
            val splashAction = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            findNavController().navigate(splashAction)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)



        mTimeCounter.start()
    }
}