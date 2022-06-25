package com.example.buyauto_app.presentation.add_car_screen.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyauto_app.databinding.ListBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListBottomSheet: BottomSheetDialogFragment() {

    private var _binding:ListBottomSheetBinding? = null
    private val binding get() = _binding!!

    var data:List<BottomSheetModel> = mutableListOf()
    var onItemClick: ((BottomSheetModel) ->Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }
    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BottomSheetAdapter(data) {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}