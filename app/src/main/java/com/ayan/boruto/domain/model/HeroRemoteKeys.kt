package com.ayan.boruto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayan.boruto.util.Constant.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
