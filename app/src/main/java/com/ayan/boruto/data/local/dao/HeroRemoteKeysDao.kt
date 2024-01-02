package com.ayan.boruto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayan.boruto.domain.model.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(keys: List<HeroRemoteKeys>)

    @Query("DELETE FROM hero_remote_keys")
    suspend fun deleteAllRemoteKeys()

    @Query("SELECT * FROM hero_remote_keys WHERE id = :heroId")
    suspend fun getRemoteKeys(heroId: Int): HeroRemoteKeys?
}