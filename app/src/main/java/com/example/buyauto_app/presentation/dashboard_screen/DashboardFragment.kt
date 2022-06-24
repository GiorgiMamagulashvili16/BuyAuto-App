package com.example.buyauto_app.presentation.dashboard_screen

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyauto_app.DashGraphDirections
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.DashboardFragmentBinding
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import com.example.buyauto_app.presentation.dashboard_screen.bottom_nav.BottomNavAdapter
import com.example.buyauto_app.presentation.dashboard_screen.bottom_nav.BottomNavModel
import kotlin.reflect.KClass

class DashboardFragment : BaseFragment<DashboardFragmentBinding,DashboardViewModel>() {
    override val viewModelClass: KClass<DashboardViewModel>
        get() = DashboardViewModel::class

    override fun inflateFragment(): Inflate<DashboardFragmentBinding> {
        return DashboardFragmentBinding::inflate
    }

    private lateinit var navController: NavController
    override fun onBindViewModel(viewModel: DashboardViewModel) {
        initBottomNavRecyclerView()
        initDashboardNavController()
    }

    private fun initDashboardNavController() {
        val host = childFragmentManager.findFragmentById(R.id.dashboardFragmentContainer) as NavHostFragment
        navController = host.findNavController()
    }

    private fun initBottomNavRecyclerView() {
        binding.bottomNavRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = BottomNavAdapter(
                listOf(
                    BottomNavModel(
                        R.drawable.ic_home,
                        DashGraphDirections.actionGlobalCarsFragment(),
                        R.id.carsFragment
                    ),
                    BottomNavModel(
                        R.drawable.ic_add_circle,
                        DashGraphDirections.actionGlobalAddCarFragment(),
                        R.id.addCarFragment
                    ),
                    BottomNavModel(
                        R.drawable.ic_heart,
                        DashGraphDirections.actionGlobalFavoritesFragment(),
                        R.id.favoritesFragment,
                    ),
                    BottomNavModel(
                        R.drawable.ic_person,
                        DashGraphDirections.actionGlobalProfileFragment(),
                        R.id.profileFragment
                    )
                )
            ) {
                if (navController.currentDestination?.id != it.fragmentId) {
                    navController.navigate(it.action)
                }
            }
        }
    }
}