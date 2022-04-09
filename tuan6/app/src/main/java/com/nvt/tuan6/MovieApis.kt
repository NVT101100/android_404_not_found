package com.nvt.tuan6

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {

    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp

    //    ///movie/upcoming
    @GET("movie/top_rated")
    suspend fun listRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp
}