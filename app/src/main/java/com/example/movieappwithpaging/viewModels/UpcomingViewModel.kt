package com.example.movieappwithpaging.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieappwithpaging.paging.PopularDataSource
import com.example.movieappwithpaging.paging.TopRatedDataSource
import com.example.movieappwithpaging.paging.UpcomingDataSource
import uz.mobiler.paging3g18.retrofit.ApiService

class UpcomingViewModel(val apiService: ApiService) : ViewModel() {

    val upComing = Pager(PagingConfig(1)) {
        UpcomingDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}