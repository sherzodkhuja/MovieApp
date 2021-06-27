package com.example.movieappwithpaging.models.imagemodel

data class ImagesModel(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)