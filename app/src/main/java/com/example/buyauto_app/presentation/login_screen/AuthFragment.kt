package com.example.buyauto_app.presentation.login_screen

import com.example.buyauto_app.databinding.AuthFragmentBinding
import com.example.buyauto_app.domain.extensions.setPasswordToggle
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass


class AuthFragment : BaseFragment<AuthFragmentBinding, AuthViewModel>() {
    override val viewModelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class

    var isPasswordVisible = false
    override fun inflateFragment(): Inflate<AuthFragmentBinding> {
        return AuthFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: AuthViewModel) {

        binding.passwordEditText.setPasswordToggle()
    }
}