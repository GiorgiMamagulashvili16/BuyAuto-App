package com.example.buyauto_app.presentation.add_car_screen.bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buyauto_app.databinding.CarModelListItemBinding

class BottomSheetAdapter(
    private val data: List<BottomSheetModel>,
    private val onItemClick: (BottomSheetModel) -> Unit
) : RecyclerView.Adapter<BottomSheetAdapter.VH>() {

    class VH(val binding: CarModelListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BottomSheetModel) = with(binding) {
            item.icon?.let { iconImageView.setImageResource(it) }
            titleTextView.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh =
            VH(CarModelListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        vh.binding.root.setOnClickListener {
            val item = data[vh.adapterPosition]
            onItemClick.invoke(item)
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

data class BottomSheetModel(
    val icon: Int? = null,
    val title: String,
    val type: Int? = null
)