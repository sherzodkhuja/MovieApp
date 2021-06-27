package com.example.movieappwithpaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappwithpaging.databinding.ItemPopularBinding
import com.example.movieappwithpaging.models.popularmodel.Result
import com.example.movieappwithpaging.utils.loadImage

class PopularPagingAdapter(var onItemClickListener: OnItemClickListener) : PagingDataAdapter<Result, PopularPagingAdapter.Vh>(MyDiffUtill()) {

    var imageUrl = "https://image.tmdb.org/t/p/w500///"

    class MyDiffUtill : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
        //    List<LinkedTreeMap<String, String>>
    }

    inner class Vh(var itemPopularBinding: ItemPopularBinding) :
            RecyclerView.ViewHolder(itemPopularBinding.root) {

        fun onBind(result: Result) {

            itemPopularBinding.item.setOnClickListener {
                onItemClickListener.onItemClick(result)
            }
            itemPopularBinding.movieImage.loadImage(imageUrl + result.poster_path)
            itemPopularBinding.movieName.text = result.original_title
            itemPopularBinding.movieRating.text = "${result.vote_average}/10"
            itemPopularBinding.releaseDate.text = result.release_date
            itemPopularBinding.popularity.text = result.popularity.toString()
        }
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface OnItemClickListener {
        fun onItemClick(result: Result)
    }
}