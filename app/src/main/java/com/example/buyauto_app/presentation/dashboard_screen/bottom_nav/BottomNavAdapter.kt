package com.example.buyauto_app.presentation.dashboard_screen.bottom_nav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.buyauto_app.R
import com.example.buyauto_app.databinding.BottomNavItemBinding

class BottomNavAdapter(
    private val data: List<BottomNavModel>,
    private val onItemClick: (BottomNavModel) -> Unit
) : RecyclerView.Adapter<BottomNavAdapter.VH>() {

    private var selectedItemIndex = 0

    class VH(val binding: BottomNavItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(BottomNavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        with(holder.binding) {
            iconImageView.setImageResource(item.icon)
            root.setOnClickListener {
                selectedItemIndex = holder.adapterPosition
                onItemClick.invoke(item)
                notifyDataSetChanged()
            }
            indicatorView.isVisible = selectedItemIndex == holder.adapterPosition
            iconImageView.setColorFilter(root.context.resources.getColor(
                if (selectedItemIndex == holder.adapterPosition){
                    R.color.bg_black
                }else{
                    android.R.color.darker_gray
                }
            ))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}