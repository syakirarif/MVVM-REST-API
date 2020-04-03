package com.arifudesu.mvvmrestapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.model.AnimeFavorite

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entry: AnimeEntry)

    @Query("SELECT * FROM tb_anime")
    fun getEntry(): List<AnimeEntry>

    @Query("SELECT COUNT(id) FROM tb_anime")
    fun countEntry(): Int

    @Query("DELETE FROM tb_anime")
    fun clearTable()

    @Query("UPDATE tb_anime SET isFavorite = :isFavorite WHERE malId = :malId")
    fun updateFavoriteStatus(isFavorite: Boolean, malId: String)

    @Query("SELECT * FROM tb_anime WHERE isFavorite = :isFavorite")
    fun getEntryFavorite(isFavorite: Boolean? = true): List<AnimeEntry>

    //--------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(entry: AnimeFavorite)

    @Query("SELECT * FROM tb_favorite")
    fun getFavorite(): List<AnimeFavorite>

    @Query("SELECT COUNT(id) FROM tb_favorite")
    fun countFavorite(): Int

    @Query("DELETE FROM tb_favorite")
    fun clearAllFavorite()

    @Query("DELETE FROM tb_favorite WHERE malId = :malId")
    fun clearFavorite(malId: String)

    @Query("SELECT * FROM tb_favorite WHERE malId = :malId")
    fun isAnimeFavorite(malId: String): List<AnimeFavorite>

}