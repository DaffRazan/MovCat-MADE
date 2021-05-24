package com.daffa.moviecatalogue.core.data.source.local.room.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity::class, com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): com.daffa.moviecatalogue.core.data.source.local.room.local.FilmDao

}