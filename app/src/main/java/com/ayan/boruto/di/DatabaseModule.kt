package com.ayan.boruto.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayan.boruto.data.local.BorutoDb
import com.ayan.boruto.util.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBorutoDatabase(@ApplicationContext context: Context): RoomDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = BorutoDb::class.java,
            name = DATABASE_NAME
        ).build()
    }

}