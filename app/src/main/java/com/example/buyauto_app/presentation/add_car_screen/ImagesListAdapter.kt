package com.example.buyauto_app.presentation.add_car_screen

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buyauto_app.databinding.ImagesItemBinding

class ImagesListAdapter(
    private val data:List<Uri>
) : RecyclerView.Adapter<ImagesListAdapter.VH>() {

    class VH( val binding: ImagesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ImagesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.imageView.setImageURI(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}