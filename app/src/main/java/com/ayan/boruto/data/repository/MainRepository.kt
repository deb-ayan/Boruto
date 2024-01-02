package com.ayan.boruto.data.repository

import androidx.paging.PagingData
import com.ayan.boruto.domain.model.Hero
import com.ayan.boruto.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remoteDataSource.getAllHeroes()
    }
}