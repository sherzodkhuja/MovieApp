package com.example.movieappwithpaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappwithpaging.databinding.ItemUpcomingBinding
import com.example.movieappwithpaging.utils.loadImage

class UpcomingPagingAdapter(var onItemClickListener: OnItemClickListener) : PagingDataAdapter<com.example.movieappwithpaging.models.upcomingModel.Result, UpcomingPagingAdapter.Vh>(MyDiffUtill()) {

    var imageUrl = "https://image.tmdb.org/t/p/w500///"
    class MyDiffUtill : DiffUtil.ItemCallback<com.example.movieappwithpaging.models.upcomingModel.Result>() {
        override fun areItemsTheSame(oldItem: com.example.movieappwithpaging.models.upcomingModel.Result, newItem: com.example.movieappwithpaging.models.upcomingModel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.example.movieappwithpaging.models.upcomingModel.Result, newItem: com.example.movieappwithpaging.models.upcomingModel.Result): Boolean {
            return oldItem == newItem
        }
        //    List<LinkedTreeMap<String, String>>
    }

    inner class Vh(var itemUpcomingBinding: ItemUpcomingBinding) :
        RecyclerView.ViewHolder(itemUpcomingBinding.root) {

        fun onBind(result: com.example.movieappwithpaging.models.upcomingModel.Result) {

            itemUpcomingBinding.item.setOnClickListener {
                onItemClickListener.onItemClick(result)
            }

            itemUpcomingBinding.movieImage.loadImage(imageUrl+result.poster_path)
            itemUpcomingBinding.movieName.text = result.original_title
            itemUpcomingBinding.releaseDateMaximum.text = result.release_date
            itemUpcomingBinding.movieRating.text =  "${result.vote_average}/10"
            itemUpcomingBinding.popularity.text = result.popularity.toString()
     }
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface OnItemClickListener {
        fun onItemClick(result: com.example.movieappwithpaging.models.upcomingModel.Result)
    }
}