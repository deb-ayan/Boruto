package com.ayan.boruto.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayan.boruto.domain.model.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllHeroes(heroes: List<Hero>)

    @Query("SELECT * FROM heroes_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM heroes_table WHERE id = :heroId")
    suspend fun getSelectedHero(heroId: Int): Hero

    @Query("DELETE FROM heroes_table")
    suspend fun deleteAllHeroes()
}