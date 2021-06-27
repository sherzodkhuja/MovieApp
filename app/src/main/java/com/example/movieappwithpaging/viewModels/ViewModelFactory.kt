package com.example.movieappwithpaging.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.mobiler.paging3g18.retrofit.ApiService
import uz.mobiler.paging3g18.viewmodels.PopularViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            return PopularViewModel(apiService) as T
        }else if (modelClass.isAssignableFrom(TopRatedViewModel::class.java)){
            return TopRatedViewModel(apiService) as T
        }else if (modelClass.isAssignableFrom(UpcomingViewModel::class.java)){
            return UpcomingViewModel(apiService) as T
        }
        throw IllegalArgumentException("Error")
    }
}