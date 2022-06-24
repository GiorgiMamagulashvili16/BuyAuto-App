package com.example.buyauto_app.presentation.cars_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buyauto_app.databinding.ViewPagerItemBinding

class ViewPagerAdapter(
    private var data: List<String>,
) : RecyclerView.Adapter<ViewPagerAdapter.VH>() {

    class VH(val binding: ViewPagerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(holder.binding.root.context).load(data[position]).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}