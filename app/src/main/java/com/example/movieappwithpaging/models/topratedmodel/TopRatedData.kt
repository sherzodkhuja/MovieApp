package com.example.movieappwithpaging.models.topratedmodel

data class TopRatedData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)   