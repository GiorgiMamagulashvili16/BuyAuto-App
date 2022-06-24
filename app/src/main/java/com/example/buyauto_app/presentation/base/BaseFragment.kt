package com.example.buyauto_app.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope
import kotlin.reflect.KClass

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment(), KoinScopeComponent {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    lateinit var viewModel: VM
    abstract val viewModelClass: KClass<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(clazz = viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateFragment().invoke(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
    }


    abstract fun inflateFragment(): Inflate<VB>
    abstract fun onBindViewModel(viewModel: VM)

    override val scope: Scope by lazy { createScope(this) }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.close()
        _binding = null
    }
}