package com.ayan.boruto.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ayan.boruto.domain.model.Hero
import com.ayan.boruto.data.local.BorutoDb
import com.ayan.boruto.data.network.BorutoApi
import com.ayan.boruto.domain.model.HeroRemoteKeys
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDb: BorutoDb
) : RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDb.heroDao()
    private val remoteKeysDao = borutoDb.heroRemoteKeysDao()

    private suspend fun getRemoteKeyClosestToCurrentPos(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        val remoteKey = state.anchorPosition?.let {pos->
            val heroItem = state.closestItemToPosition(pos)
            heroItem?.id?.let {heroId->
                remoteKeysDao.getRemoteKeys(heroId)
            }
        }
        return remoteKey
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        val heroItem = state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()
        return heroItem?.let {
            remoteKeysDao.getRemoteKeys(it.id)
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        val heroItem = state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
        return heroItem?.let {
            remoteKeysDao.getRemoteKeys(it.id)
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try{
            val page = when(loadType){
                LoadType.REFRESH->{
                    val remoteKey = getRemoteKeyClosestToCurrentPos(state)
                    remoteKey?.nextPage?.minus(1)?: 1
                }
                LoadType.PREPEND->{
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)
                    prevPage
                }
                LoadType.APPEND->{
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage?: return MediatorResult.Success(endOfPaginationReached = remoteKey!=null)
                    nextPage
                }
            }
            val response = borutoApi.getAllHeroes(page = page)
            val listOfHeroes = response.heroes
            if(listOfHeroes.isNotEmpty()){
                borutoDb.withTransaction{
                    if(loadType == LoadType.REFRESH){
                        heroDao.deleteAllHeroes()
                        remoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map{hero->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    remoteKeysDao.addAllRemoteKeys(keys = keys)
                    heroDao.addAllHeroes(heroes = listOfHeroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        }catch (e : Exception){
            MediatorResult.Error(e)
        }
    }

}