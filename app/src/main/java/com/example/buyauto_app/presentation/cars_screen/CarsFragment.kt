package com.example.buyauto_app.presentation.cars_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.CarsFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class CarsFragment : BaseFragment<CarsFragmentBinding,CarsViewModel>() {
    override val viewModelClass: KClass<CarsViewModel>
        get() = CarsViewModel::class

    override fun inflateFragment(): Inflate<CarsFragmentBinding> {
        return CarsFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: CarsViewModel) {

    }


}