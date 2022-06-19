package com.example.buyauto_app.presentation.profile_screen

import com.example.buyauto_app.databinding.ProfileFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class ProfileFragment : BaseFragment<ProfileFragmentBinding, ProfileViewModel>() {
    override val viewModelClass: KClass<ProfileViewModel>
        get() = ProfileViewModel::class

    override fun inflateFragment(): Inflate<ProfileFragmentBinding> {
        return ProfileFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: ProfileViewModel) {

    }

}