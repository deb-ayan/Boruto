package com.ayan.boruto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayan.boruto.domain.model.Hero
import com.ayan.boruto.domain.model.HeroRemoteKeys
import com.ayan.boruto.data.local.dao.HeroDao
import com.ayan.boruto.data.local.dao.HeroRemoteKeysDao

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class BorutoDb : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}