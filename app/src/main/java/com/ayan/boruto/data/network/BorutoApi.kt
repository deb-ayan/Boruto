package com.ayan.boruto.data.network

import com.ayan.boruto.domain.model.ApiResponse
import com.ayan.boruto.util.Constant.GET_ALL_HEROES
import com.ayan.boruto.util.Constant.SEARCH_HEROES
import retrofit2.http.GET
import retrofit2.http.Query


interface BorutoApi {
    @GET(GET_ALL_HEROES)
    suspend fun getAllHeroes(@Query("page") page: Int): ApiResponse

    @GET(SEARCH_HEROES)
    suspend fun searchHeroes(@Query("name") name: String): ApiResponse
}