package com.example.movieappwithpaging.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieappwithpaging.paging.TopRatedDataSource
import uz.mobiler.paging3g18.retrofit.ApiService

class TopRatedViewModel(val apiService: ApiService) : ViewModel() {

    val topRated = Pager(PagingConfig(1)) {
        TopRatedDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}