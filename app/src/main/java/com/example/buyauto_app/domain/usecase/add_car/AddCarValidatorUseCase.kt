package com.example.buyauto_app.domain.usecase.add_car

import android.net.Uri
import com.example.buyauto_app.R
import com.example.buyauto_app.domain.usecase.auth.ValidatorResult
import com.example.buyauto_app.domain.util.StringResourceProvider

class AddCarValidatorUseCase(private val resourceProvider: StringResourceProvider) {

    fun validate(
        lat: Double?,
        long: Double?,
        manufacture: String,
        model: String,
        year: String,
        description: String,
        imageList:List<Uri>?
    ): ValidatorResult {
        return when {
            lat == null || long == null || lat == 0.0 || long == 0.0 || imageList.isNullOrEmpty()-> ValidatorResult(
                false, resourceProvider.getString(R.string.please_fill_all_field)
            )
            manufacture.isBlank() || model.isBlank() || year.isBlank() || description.isBlank() -> ValidatorResult(
                false, resourceProvider.getString(R.string.please_fill_all_field)
            )
            else -> ValidatorResult()
        }
    }
}