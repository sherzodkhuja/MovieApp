package uz.mobiler.paging3g18.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieappwithpaging.paging.PopularDataSource
import uz.mobiler.paging3g18.retrofit.ApiService

class PopularViewModel(val apiService: ApiService) : ViewModel() {

    val populars = Pager(PagingConfig(1)) {
        PopularDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}