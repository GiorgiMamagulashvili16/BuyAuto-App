package com.example.buyauto_app.domain.usecase.auth

import com.example.buyauto_app.R
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.util.StringResourceProvider
import com.example.buyauto_app.domain.util.extensions.isNotValidEmail

class AuthValidatorUseCase(private val resource: StringResourceProvider) {

    fun validateSignIn(email: String, password: String): AuthValidatorResult {
        return when {
            email.isBlank() || password.isBlank() -> AuthValidatorResult(
                false,
                resource.getString(R.string.please_fill_all_field)
            )
            email.isNotValidEmail() -> AuthValidatorResult(
                false,
                resource.getString(R.string.incorrect_email)
            )
            password.length <= 3 -> AuthValidatorResult(
                false,
                resource.getString(R.string.password_is_too_short)
            )
            else -> AuthValidatorResult()
        }
    }

    fun validateSignUp(user: User, repeatPassword: String): AuthValidatorResult {
        with(user) {
            return when {
                email.isBlank() || password.isNullOrBlank() || username.isBlank() || repeatPassword.isBlank() -> AuthValidatorResult(
                    false,
                    resource.getString(R.string.please_fill_all_field)
                )
                email.isNotValidEmail() -> AuthValidatorResult(
                    false,
                    resource.getString(R.string.incorrect_email)
                )
                password.length <= 3 -> AuthValidatorResult(
                    false,
                    resource.getString(R.string.password_is_too_short)
                )
                password != repeatPassword -> AuthValidatorResult(
                    false,
                    resource.getString(R.string.passwords_does_not_matches)
                )

                else -> AuthValidatorResult()
            }
        }
    }
}