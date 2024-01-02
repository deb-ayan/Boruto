package com.ayan.boruto.domain.repository

import androidx.paging.PagingData
import com.ayan.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}