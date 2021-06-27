package uz.mobiler.paging3g18.retrofit

import com.example.movieappwithpaging.models.imagemodel.ImagesModel
import com.example.movieappwithpaging.models.popularmodel.PopularData
import com.example.movieappwithpaging.models.topratedmodel.TopRatedData
import com.example.movieappwithpaging.models.upcomingModel.UpcomingData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //popular?api_key=b8e4ead2a823c6e5c43ea97765f8c0d5&page=1
    @GET("popular")
    suspend fun getPopulars(
            @Query("api_key") api_key: String = "b8e4ead2a823c6e5c43ea97765f8c0d5",
            @Query("page") page: Int
    ): PopularData

    //top_rated?api_key=b8e4ead2a823c6e5c43ea97765f8c0d5&language=US&page=1
    @GET("top_rated")
    suspend fun getTopRated(
            @Query("api_key") api_key: String = "b8e4ead2a823c6e5c43ea97765f8c0d5",
            @Query("page") page: Int
    ): TopRatedData

    //upcoming?api_key=b8e4ead2a823c6e5c43ea97765f8c0d5&page=1
    @GET("upcoming")
    suspend fun getUpcoming(
            @Query("api_key") api_key: String = "b8e4ead2a823c6e5c43ea97765f8c0d5",
            @Query("page") page: Int
    ): UpcomingData

    //337404/images?api_key=b8e4ead2a823c6e5c43ea97765f8c0d5
    @GET("{id}/images?")
    suspend fun getImages(
            @Path("id") id: Int,
            @Query("api_key") api_key: String = "b8e4ead2a823c6e5c43ea97765f8c0d5"
    ): ImagesModel

}