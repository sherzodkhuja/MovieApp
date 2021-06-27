package com.example.movieappwithpaging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.mobiler.paging3g18.retrofit.ApiService
import java.lang.Exception

class TopRatedDataSource(val apiService: ApiService) : PagingSource<Int, com.example.movieappwithpaging.models.topratedmodel.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.movieappwithpaging.models.topratedmodel.Result> {
        try {
            //1-page ni ovoladi starting page (position) sifatida
            val nextPageNumber = params.key ?: 1

            //linkga pageNumber ni berib yuboradi
            val passengers = apiService.getTopRated("b8e4ead2a823c6e5c43ea97765f8c0d5", nextPageNumber)

            //1-holatda else ga tuwadi, va page ga +1 qo'wadi. Keyingi holatlada if ga tuwadi,
            // va previous page ga page-1 qiladi va next page ga page+1 qladi va linkga wu organi berib turadi
            if (nextPageNumber > 1) {
                return LoadResult.Page(passengers.results, nextPageNumber - 1, nextPageNumber + 1)
            } else {
                return LoadResult.Page(passengers.results, null, nextPageNumber + 1)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.example.movieappwithpaging.models.topratedmodel.Result>): Int? {
        return state.anchorPosition
    }

}