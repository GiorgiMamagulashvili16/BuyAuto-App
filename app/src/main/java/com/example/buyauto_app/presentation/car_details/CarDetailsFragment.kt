package com.example.buyauto_app.presentation.car_details

import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.buyauto_app.databinding.CarDetailsFragmentBinding
import com.example.buyauto_app.domain.model.CarItem
import com.example.buyauto_app.domain.util.extensions.liveDataObserver
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import com.example.buyauto_app.presentation.cars_screen.ViewPagerAdapter
import java.util.*
import kotlin.reflect.KClass


class CarDetailsFragment : BaseFragment<CarDetailsFragmentBinding, CarDetailsViewModel>() {
    override val viewModelClass: KClass<CarDetailsViewModel>
        get() = CarDetailsViewModel::class

    override fun inflateFragment(): Inflate<CarDetailsFragmentBinding> {
        return CarDetailsFragmentBinding::inflate
    }

    private val args: CarDetailsFragmentArgs by navArgs()

    override fun onBindViewModel(viewModel: CarDetailsViewModel) {
        viewModel.setCarItem(args.carItem)
        observeCarItem(viewModel)
        setListeners(viewModel)
    }

    private fun setListeners(viewModel: CarDetailsViewModel) = with(binding) {
        locationButton.setOnClickListener {
            val uri = String.format(
                Locale.ENGLISH,
                "geo:%f,%f",
                viewModel.carItem.value?.lat,
                viewModel.carItem.value?.long
            )
            Intent(Intent.ACTION_VIEW, Uri.parse(uri)).also {
                requireContext().startActivity(it)
            }
        }
        getNumberButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${viewModel.carItem.value?.ownerNumber}")
            startActivity(intent)
        }
        interestedButton.setOnClickListener {
            viewModel.sendNotification(viewModel.carItem.value?.ownerId!!,viewModel.carItem.value?.ownerUserName!!,viewModel.carItem.value?.model!!)
        }
    }

    private fun observeCarItem(viewModel: CarDetailsViewModel) {
        liveDataObserver(viewModel.carItem) {
            setCarData(it)
        }
    }

    private fun setCarData(carItem: CarItem) = with(binding) {
        with(carItem) {
            manufactureTextView.text = manufacture
            modelTextView.text = "${model} $year"
            descriptionTextView.text = description
            carImageList?.let {
                imagesViewPager.adapter = ViewPagerAdapter(it)
                imagesViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                indicator.setViewPager(imagesViewPager)
            }
        }
    }
}