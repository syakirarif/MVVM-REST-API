package com.arifudesu.mvvmrestapi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry
import com.arifudesu.mvvmrestapi.util.DataConverter

@Database(
    entities = [
        AnimeEntry::class,
        AnimeTopEntry::class,
        AnimeTopUpcomingEntry::class,
        AnimeFavoriteEntry::class,
        DetailAnimeEntry::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DataConverter::class)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var instance: AnimeDatabase? = null

        operator fun invoke(context: Context): AnimeDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AnimeDatabase::class.java,
                "sidago.db"
            )
                .allowMainThreadQueries()
                .build()
    }
}
