package com.example.buyauto_app.presentation.splash_screen

import androidx.navigation.fragment.findNavController
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.SplashFragmentBinding
import com.example.buyauto_app.domain.util.extensions.liveDataObserver
import com.example.buyauto_app.domain.util.extensions.setAfterAnimOver
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
        viewModel.checkIsUserLogged()

        liveDataObserver(viewModel.isUserLogged) {
          binding.root.setAfterAnimOver {
              if (it)
                  findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
              else
                  findNavController().navigate(R.id.action_splashFragment_to_authFragment)
          }
        }
    }

}