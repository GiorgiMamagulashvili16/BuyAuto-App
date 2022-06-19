package com.example.buyauto_app.presentation.dashboard_screen

import com.example.buyauto_app.databinding.DashboardFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class DashboardFragment : BaseFragment<DashboardFragmentBinding,DashboardViewModel>() {
    override val viewModelClass: KClass<DashboardViewModel>
        get() = DashboardViewModel::class

    override fun inflateFragment(): Inflate<DashboardFragmentBinding> {
        return DashboardFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: DashboardViewModel) {

    }

}