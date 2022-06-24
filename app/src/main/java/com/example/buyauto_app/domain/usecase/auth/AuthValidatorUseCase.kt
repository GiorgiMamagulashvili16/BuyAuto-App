package com.example.buyauto_app.domain.usecase.auth

import com.example.buyauto_app.R
import com.example.buyauto_app.domain.model.User
import com.example.buyauto_app.domain.util.StringResourceProvider
import com.example.buyauto_app.domain.util.extensions.isNotValidEmail

class AuthValidatorUseCase(private val resource: StringResourceProvider) {

    fun validateSignIn(email: String, password: String): ValidatorResult {
        return when {
            email.isBlank() || password.isBlank() -> ValidatorResult(
                false,
                resource.getString(R.string.please_fill_all_field)
            )
            email.isNotValidEmail() -> ValidatorResult(
                false,
                resource.getString(R.string.incorrect_email)
            )
            password.length <= 3 -> ValidatorResult(
                false,
                resource.getString(R.string.password_is_too_short)
            )
            else -> ValidatorResult()
        }
    }

    fun validateSignUp(user: User, repeatPassword: String): ValidatorResult {
        with(user) {
            return when {
                email.isBlank() || password.isNullOrBlank() || username.isBlank() || repeatPassword.isBlank() -> ValidatorResult(
                    false,
                    resource.getString(R.string.please_fill_all_field)
                )
                email.isNotValidEmail() -> ValidatorResult(
                    false,
                    resource.getString(R.string.incorrect_email)
                )
                password.length <= 3 -> ValidatorResult(
                    false,
                    resource.getString(R.string.password_is_too_short)
                )
                password != repeatPassword -> ValidatorResult(
                    false,
                    resource.getString(R.string.passwords_does_not_matches)
                )

                else -> ValidatorResult()
            }
        }
    }
}