package com.ayan.boruto.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ayan.boruto.data.local.BorutoDb
import com.ayan.boruto.data.network.BorutoApi
import com.ayan.boruto.data.paging_source.HeroRemoteMediator
import com.ayan.boruto.domain.model.Hero
import com.ayan.boruto.domain.repository.RemoteDataSource
import com.ayan.boruto.util.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDb: BorutoDb
): RemoteDataSource {
    private val heroDao = borutoDb.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDao.getAllHeroes()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi, borutoDb),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }

}