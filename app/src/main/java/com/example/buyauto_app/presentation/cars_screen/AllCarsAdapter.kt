package com.example.buyauto_app.presentation.cars_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.buyauto_app.databinding.CarListItemBinding
import com.example.buyauto_app.domain.model.CarItem

class AllCarsAdapter(
    private val data: List<CarItem>,
    private val itemClickListener: (CarItem) -> Unit
) : RecyclerView.Adapter<AllCarsAdapter.VH>() {

    class VH(val binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CarItem) {
            with(binding) {
                priceTextView.text = "${item.price}$"
                manufactureTextView.text = item.manufacture
                modelTextView.text = "${item.model} \n ${item.year}"
                item.carImageList?.let {
                    imageViewPager.adapter = ViewPagerAdapter(it)
                    imageViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    indicator.setViewPager(imageViewPager)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh = VH(CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        vh.binding.openButton.setOnClickListener {
            val item = data[vh.adapterPosition]
            itemClickListener.invoke(item)
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}