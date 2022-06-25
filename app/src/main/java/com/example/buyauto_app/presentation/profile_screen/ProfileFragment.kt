package com.example.buyauto_app.presentation.profile_screen

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.ProfileFragmentBinding
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.util.extensions.createSnackBar
import com.example.buyauto_app.domain.util.extensions.liveDataObserver
import com.example.buyauto_app.presentation.add_car_screen.bottom_sheet.BottomSheetModel
import com.example.buyauto_app.presentation.add_car_screen.bottom_sheet.ListBottomSheet
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import com.example.buyauto_app.presentation.cars_screen.AllCarsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.reflect.KClass

class ProfileFragment : BaseFragment<ProfileFragmentBinding, ProfileViewModel>() {
    override val viewModelClass: KClass<ProfileViewModel>
        get() = ProfileViewModel::class

    override fun inflateFragment(): Inflate<ProfileFragmentBinding> {
        return ProfileFragmentBinding::inflate
    }

    private val listBottomSheet: ListBottomSheet by lazy { ListBottomSheet() }

    override fun onBindViewModel(viewModel: ProfileViewModel) {
        viewModel.setCarList()
        observeScreenState(viewModel)
        setListeners(viewModel)
    }

    private fun observeScreenState(viewModel: ProfileViewModel) =
        liveDataObserver(viewModel.screenState) { state ->
            state.errorMessage?.let {
                createSnackBar(it) {}
            }
            state.data?.let {
                setUpAllData(it)
            }
            if (state.isLogOuted){
                val navController = findNavController()
                navController.setGraph(R.navigation.main_graph)
                navController.navigate(R.id.action_global_authFragment)
            }
            binding.loadingProgressBar.isVisible = state.isLoading
        }

    private fun setUpAllData(data: List<CarItem>) {
        binding.carsRecyclerView.apply {
            adapter = AllCarsAdapter(data) {}
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setListeners(viewModel: ProfileViewModel) = with(binding) {
        settingImageView.setOnClickListener {
            setUpBottomSheet(
                listOf(
                    BottomSheetModel(
                        R.drawable.ic_arrow_back,
                        "log out"
                    )
                )
            ) {
                FirebaseAuth.getInstance().signOut()
                val navController = findNavController()
                navController.setGraph(R.navigation.main_graph)
                navController.navigate(R.id.action_global_authFragment)
            }
        }
    }

    private fun setUpBottomSheet(dataList: List<BottomSheetModel>, onTitleClick: (String) -> Unit) {
        listBottomSheet.apply {
            data = dataList
            onItemClick = { model ->
                onTitleClick.invoke(model.title)
                dismiss()
            }
        }
        listBottomSheet.show(childFragmentManager, "d")
    }
}