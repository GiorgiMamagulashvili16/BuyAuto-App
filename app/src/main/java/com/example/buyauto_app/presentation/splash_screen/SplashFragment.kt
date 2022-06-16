package com.example.buyauto_app.presentation.splash_screen

import com.example.buyauto_app.databinding.SplashFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<SplashFragmentBinding,SplashViewModel>() {
    override val viewModelClass: KClass<SplashViewModel>
        get() = SplashViewModel::class

    override fun inflateFragment(): Inflate<SplashFragmentBinding> {
        return SplashFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: SplashViewModel) {

    }

}