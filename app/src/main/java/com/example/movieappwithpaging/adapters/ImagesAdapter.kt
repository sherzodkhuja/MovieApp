package com.example.movieappwithpaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappwithpaging.databinding.ItemImagesBinding
import com.example.movieappwithpaging.models.imagemodel.Backdrop
import com.squareup.picasso.Picasso

class ImagesAdapter(var backdrops: List<Backdrop>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ImagesAdapter.Vh>() {
    inner class Vh(var itemImagesBinding: ItemImagesBinding) :
            RecyclerView.ViewHolder(itemImagesBinding.root) {
        var imageUrl = "https://image.tmdb.org/t/p/w500///"
        fun onBind(backdrop: Backdrop, position: Int) {
            Picasso.get().load(imageUrl + backdrop.file_path).into(itemImagesBinding.movieImages)
            itemImagesBinding.movieImages.setOnClickListener {
                onItemClickListener.onItemClick(backdrops[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(backdrops[position], position)
    }

    override fun getItemCount(): Int {
        return backdrops.size
    }

    interface OnItemClickListener {
        fun onItemClick(backdrop: Backdrop)
    }
}