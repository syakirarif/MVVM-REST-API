package com.arifudesu.mvvmrestapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry

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


    //-------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimeTop(entry: AnimeTopEntry)

    @Query("SELECT * FROM tb_anime_top")
    fun getAnimeTop(): List<AnimeTopEntry>

    @Query("SELECT COUNT(id) FROM tb_anime_top")
    fun countAnimeTop(): Int

    @Query("DELETE FROM tb_anime_top")
    fun clearTableAnimeTop()

    //-------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimeTopUpcoming(entry: AnimeTopUpcomingEntry)

    @Query("SELECT * FROM tb_anime_top_upcoming")
    fun getAnimeTopUpcoming(): List<AnimeTopUpcomingEntry>

    @Query("SELECT COUNT(id) FROM tb_anime_top_upcoming")
    fun countAnimeTopUpcoming(): Int

    @Query("DELETE FROM tb_anime_top_upcoming")
    fun clearTableAnimeTopUpcoming()

}