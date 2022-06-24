package com.example.buyauto_app.presentation.cars_screen

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.CarsFragmentBinding
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.util.extensions.createSnackBar
import com.example.buyauto_app.domain.util.extensions.liveDataObserver
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
        viewModel.getAllCarsData()
        observeScreenState(viewModel)
    }

    private fun observeScreenState(viewModel: CarsViewModel) {
        liveDataObserver(viewModel.screenState) { state ->
            state.allCarsData?.let {
                setUpAllData(it)
            }
            state.errorMessage?.let {
                createSnackBar(state.errorMessage) {}
            }
            binding.loadingProgressBar.isVisible = state.isLoading
        }
    }

    private fun setUpAllData(data: List<CarItem>) {
        binding.allCarsRecyclerView.apply {
            adapter = AllCarsAdapter(data) {

                findNavController().navigate(
                    CarsFragmentDirections.actionGlobalCarDetailsFragment(it)
                )
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}