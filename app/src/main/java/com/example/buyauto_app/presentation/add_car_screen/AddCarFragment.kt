package com.example.buyauto_app.presentation.add_car_screen

import com.example.buyauto_app.databinding.AddCarFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class AddCarFragment : BaseFragment<AddCarFragmentBinding, AddCarViewModel>() {
    override val viewModelClass: KClass<AddCarViewModel>
        get() = AddCarViewModel::class

    override fun inflateFragment(): Inflate<AddCarFragmentBinding> {
        return AddCarFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: AddCarViewModel) {

    }

}