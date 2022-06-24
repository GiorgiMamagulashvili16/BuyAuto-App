package com.example.buyauto_app.presentation.add_car_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buyauto_app.databinding.ListBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListBottomSheet: BottomSheetDialogFragment() {

    private var _binding:ListBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListBottomSheetBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}