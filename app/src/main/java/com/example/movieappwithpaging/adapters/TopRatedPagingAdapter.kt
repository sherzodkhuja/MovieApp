package com.example.movieappwithpaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappwithpaging.databinding.ItemTopRatedBinding
import com.example.movieappwithpaging.utils.loadImage

class TopRatedPagingAdapter(var onItemClickListener: OnItemClickListener) : PagingDataAdapter<com.example.movieappwithpaging.models.topratedmodel.Result, TopRatedPagingAdapter.Vh>(MyDiffUtill()) {

    var imageUrl = "https://image.tmdb.org/t/p/w500///"
    class MyDiffUtill : DiffUtil.ItemCallback<com.example.movieappwithpaging.models.topratedmodel.Result>() {
        override fun areItemsTheSame(oldItem: com.example.movieappwithpaging.models.topratedmodel.Result, newItem: com.example.movieappwithpaging.models.topratedmodel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.example.movieappwithpaging.models.topratedmodel.Result, newItem: com.example.movieappwithpaging.models.topratedmodel.Result): Boolean {
            return oldItem == newItem
        }
        //    List<LinkedTreeMap<String, String>>
    }

    inner class Vh(var itemTopRatedBinding: ItemTopRatedBinding) :
        RecyclerView.ViewHolder(itemTopRatedBinding.root) {

        fun onBind(result: com.example.movieappwithpaging.models.topratedmodel.Result) {

            itemTopRatedBinding.item.setOnClickListener {
                onItemClickListener.onItemClick(result)
            }

            itemTopRatedBinding.movieImage.loadImage(imageUrl+result.poster_path)
            itemTopRatedBinding.movieName.text = result.original_title
            itemTopRatedBinding.movieRating.text =  "${result.vote_average}/10"
            itemTopRatedBinding.releaseDate.text = result.release_date
            itemTopRatedBinding.popularity.text = result.popularity.toString()
     }
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface OnItemClickListener {
        fun onItemClick(result: com.example.movieappwithpaging.models.topratedmodel.Result)
    }
}