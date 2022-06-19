package com.example.buyauto_app.presentation.favorites_screen

import com.example.buyauto_app.databinding.FavoritesFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import kotlin.reflect.KClass

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding, FavoritesViewModel>() {
    override val viewModelClass: KClass<FavoritesViewModel>
        get() = FavoritesViewModel::class

    override fun inflateFragment(): Inflate<FavoritesFragmentBinding> {
        return FavoritesFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: FavoritesViewModel) {

    }
}