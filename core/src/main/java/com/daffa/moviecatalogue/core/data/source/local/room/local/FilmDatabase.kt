package com.daffa.moviecatalogue.core.data.source.local.room.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

}