package com.example.buyauto_app.presentation.add_car_screen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Looper
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.buyauto_app.databinding.AddCarFragmentBinding
import com.example.buyauto_app.domain.util.extensions.createSnackBar
import com.example.buyauto_app.domain.util.extensions.liveDataObserver
import com.example.buyauto_app.presentation.add_car_screen.bottom_sheet.BottomSheetModel
import com.example.buyauto_app.presentation.add_car_screen.bottom_sheet.ListBottomSheet
import com.example.buyauto_app.presentation.base.BaseFragment
import com.example.buyauto_app.presentation.base.Inflate
import com.google.android.gms.location.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass


open class AddCarFragment : BaseFragment<AddCarFragmentBinding, AddCarViewModel>() {
    override val viewModelClass: KClass<AddCarViewModel>
        get() = AddCarViewModel::class

    override fun inflateFragment(): Inflate<AddCarFragmentBinding> {
        return AddCarFragmentBinding::inflate
    }

    private lateinit var imagesPickerLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var locationCallback: LocationCallback? = null

    private val listBottomSheet: ListBottomSheet by lazy { ListBottomSheet() }

    override fun onBindViewModel(viewModel: AddCarViewModel) {
        binding.chooseImageTextView.setOnClickListener {
            imagesPickerLauncher.launch("image/*")
        }
        setImagePickerLauncher(viewModel)
        observeImages(viewModel)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        locationPermissionsRequest()
        observeScreenState(viewModel)
        setListeners(viewModel)
    }

    private fun setListeners(viewModel: AddCarViewModel) = with(binding) {
        manufactureTextView.setOnClickListener {
            val list = mutableListOf<BottomSheetModel>()
            CarManufactures.values().forEach {
                list.add(BottomSheetModel(title = it.title, type = it.value))
            }
            setUpBottomSheet(list){
                manufactureTextView.text = it
            }
        }
        modelTextView.setOnClickListener {
            viewModel.currentManufactureType.value?.let { value ->
                when (value) {
                    CarManufactures.MERCEDES.value -> {
                        setUpBottomSheet(CarList.mercedesModels) {
                            modelTextView.text = it
                        }
                    }
                    CarManufactures.AUDI.value -> {
                        setUpBottomSheet(CarList.audiModels) {
                            modelTextView.text = it
                        }
                    }
                    CarManufactures.NISSAN.value -> {
                        setUpBottomSheet(CarList.nissanModels) {
                            modelTextView.text = it
                        }
                    }
                }
            }
        }
        yearTextView.setOnClickListener {
            setUpBottomSheet(CarList.yearsList){
                yearTextView.text = it
            }
        }
    }

    private fun setUpBottomSheet(dataList: List<BottomSheetModel>, onTitleClick: (String) -> Unit) {
        listBottomSheet.apply {
            data = dataList
            onItemClick = { model ->
                model.type?.let { it -> viewModel.setType(it) }
                onTitleClick.invoke(model.title)
                dismiss()
            }
        }
        listBottomSheet.show(childFragmentManager, "d")
    }

    private fun observeScreenState(viewModel: AddCarViewModel) {
        liveDataObserver(viewModel.screenState) {
            binding.loadingProgressBar.isVisible = it.isLoading
            if (it.isSuccessFull) {
                createSnackBar("successfully added car item") {
                    setAction("ok") { dismiss() }
                }
            }
            it.errorMessage?.let { message ->
                createSnackBar(message) {
                    setAction("ok") { dismiss() }
                }
            }
        }
    }

    private fun observeImages(viewModel: AddCarViewModel) {
        liveDataObserver(viewModel.selectedImages) {
            setUpRecyclerView(it)
            binding.chooseImageTextView.isVisible = it.isNotEmpty()
        }
    }

    private fun setImagePickerLauncher(viewModel: AddCarViewModel) {
        imagesPickerLauncher = registerForActivityResult(
            ActivityResultContracts.GetMultipleContents(),
        ) { data ->
            viewModel.setImages(data)
        }
    }

    private fun setUpRecyclerView(data: List<Uri>) {
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = ImagesListAdapter(data)
        }
    }

    private fun setLocation() {
        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(60)
            fastestInterval = TimeUnit.SECONDS.toMillis(30)
            maxWaitTime = TimeUnit.MINUTES.toMillis(1)
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                binding.saveButton.setOnClickListener {
                    locationResult.lastLocation?.let { loc -> addCarItem(loc) }
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback!!,
            Looper.getMainLooper()
        )
    }

    private fun addCarItem(location: Location) {
        with(binding) {
            viewModel.addCarItem(
                location.latitude,
                location.longitude,
                manufactureTextView.text.toString(),
                modelTextView.text.toString(),
                yearTextView.text.toString(),
                descriptionEditText.text.toString(),
                priceEditText.text.toString().toInt(),
                numberEditText.text.toString()
            )
        }
    }
    private fun locationPermissionsRequest() {
        when {
            hasFineLocationPermission() && hasCoarseLocationPermission() -> {
                setLocation()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                createSnackBar("app need this permission"){
                    setAction("ok"){
                        dismiss()
                    }
                }
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                createSnackBar("appneed this persmission"){
                    setAction("ok"){
                        dismiss()
                    }
                }
            }
            else -> requestLocationPermissions(locationPermissionsLauncher)
        }
    }

    private val locationPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perm ->
            if (perm[Manifest.permission.ACCESS_FINE_LOCATION] == true && perm[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                setLocation()
            }
        }

    override fun onPause() {
        super.onPause()
        locationCallback?.let {
            val removeLocationUpdate =
                fusedLocationProviderClient.removeLocationUpdates(it)
            removeLocationUpdate.addOnCompleteListener { task ->
                if (task.isSuccessful)
                    Log.d("RemoveLocationUpdate", "successfully removed")
                else
                    Log.d("RemoveLocationUpdate", "failure")
            }
        }
    }

    private fun hasFineLocationPermission() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun hasCoarseLocationPermission() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermissions(request: ActivityResultLauncher<Array<String>>) {
        request.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

object CarList {
    val mercedesModels = listOf(
        BottomSheetModel(title = "E-class"),
        BottomSheetModel(title = "A-class"),
        BottomSheetModel(title = "GT-class"),
        BottomSheetModel(title = "B-class"),
        BottomSheetModel(title = "S-class"),
        BottomSheetModel(title = "G-class"),
        BottomSheetModel(title = "CLS-class"),
    )
    val audiModels = listOf(
        BottomSheetModel(title = "TT"),
        BottomSheetModel(title = "S1"),
        BottomSheetModel(title = "S2"),
        BottomSheetModel(title = "S3"),
        BottomSheetModel(title = "A6"),
        BottomSheetModel(title = "A4"),
        BottomSheetModel(title = "RS6"),
    )
    val nissanModels = listOf(
        BottomSheetModel(title = "Skyline"),
        BottomSheetModel(title = "350z"),
        BottomSheetModel(title = "370z"),
        BottomSheetModel(title = "Cube"),
        BottomSheetModel(title = "Silvia"),
        BottomSheetModel(title = "X-Terra"),
    )
    val yearsList = listOf(
        BottomSheetModel(title = "1999"),
        BottomSheetModel(title = "2000"),
        BottomSheetModel(title = "2001"),
        BottomSheetModel(title = "2002"),
        BottomSheetModel(title = "2003"),
        BottomSheetModel(title = "2004"),
        BottomSheetModel(title = "2005"),
        BottomSheetModel(title = "2006"),
        BottomSheetModel(title = "2007"),
        BottomSheetModel(title = "2008"),
        BottomSheetModel(title = "2009"),
        BottomSheetModel(title = "2010"),
        BottomSheetModel(title = "2011"),
        BottomSheetModel(title = "2012"),
        BottomSheetModel(title = "2013"),
        BottomSheetModel(title = "2014"),
        BottomSheetModel(title = "2015"),
        BottomSheetModel(title = "2016"),
        BottomSheetModel(title = "2017"),
        BottomSheetModel(title = "2018"),
    )
}

enum class CarManufactures(val value: Int, val title: String) {
    MERCEDES(1, "Mercedes-Benz"), AUDI(2, "Audi"), NISSAN(3, "Nissan")
}