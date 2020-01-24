package com.arifudesu.mvvmrestapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifudesu.mvvmrestapi.model.AnimeEntry

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

}