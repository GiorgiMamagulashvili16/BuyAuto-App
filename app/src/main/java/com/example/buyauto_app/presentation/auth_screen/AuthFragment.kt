package com.example.buyauto_app.presentation.auth_screen

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.AuthFragmentBinding
import com.example.buyauto_app.domain.util.extensions.*
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass


class AuthFragment : BaseFragment<AuthFragmentBinding, AuthViewModel>() {
    override val viewModelClass: KClass<AuthViewModel>
        get() = AuthViewModel::class

    override fun inflateFragment(): Inflate<AuthFragmentBinding> {
        return AuthFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: AuthViewModel) {
        setListeners(viewModel)
        observeAuthState(viewModel)
        binding.passwordEditText.setPasswordToggle()
    }
    private fun observeAuthState(viewModel: AuthViewModel){
        liveDataObserver(viewModel.authScreenState){
            when {
                it.isLoginSuccess -> {
                    findNavController().navigate(R.id.action_authFragment_to_dashboardFragment)
                }
                it.isSignUpSuccess -> {
                    createSnackBar(getString(R.string.successfully_created_account)){
                        setAction(getString(R.string.ok)){
                            dismiss()
                        }
                    }
                    binding.root.apply {
                        setTransition(R.id.authTransition)
                        transitionToStart()
                    }
                }
                it.errorMessage != null -> {
                    createSnackBar(it.errorMessage){
                        setAction(getString(R.string.ok)){
                            dismiss()
                        }
                    }
                }
            }
            binding.loadingProgressBar.isVisible = it.isLoading
        }
    }

    private fun setListeners(viewModel: AuthViewModel) = with(binding) {
        logInButton.setOnClickListener {
            logIn(viewModel)
        }
        signUpContainer.signUpButton.setOnClickListener {
            signUp(viewModel)
        }
    }

    private fun logIn(viewModel: AuthViewModel) = with(binding) {
        viewModel.signIn(
            emailEditText.text.toString(),
            passwordEditText.text.toString()
        )
    }

    private fun signUp(viewModel: AuthViewModel) = with(binding.signUpContainer) {
        viewModel.signUp(
            emailEditText.text.toString(),
            passwordEditText.text.toString(),
            repeatPasswordEditText.text.toString(),
            usernameEditText.text.toString()
        )
    }

    private fun setPasswordEditTextsToggle() = with(binding) {
        val editTexts = listOf(
            passwordEditText,
            signUpContainer.passwordEditText,
            signUpContainer.repeatPasswordEditText
        )
        editTexts.forEach {
            it.let {
                it.setPasswordToggle()
            }
        }
    }
}