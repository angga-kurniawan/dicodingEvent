package com.example.submissionfundamentalpertama.Room

import android.app.ActionBar.Tab
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.concurrent.Task

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(table: Table)

    @Delete
    suspend fun deleteFavorite(table: Table)

    @Query("select * from table_favorite where isFavorite = 1")
    fun getFavorite(): LiveData<List<Table>>

    // Mengecek apakah ID tertentu ada di dalam tabel favorit
    @Query("SELECT id FROM table_favorite WHERE id = :id LIMIT 1")
    suspend fun getFavoriteId(id: Int): List<Int>
}