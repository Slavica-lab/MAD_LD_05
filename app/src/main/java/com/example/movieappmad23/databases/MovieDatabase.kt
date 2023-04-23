package com.example.movieappmad23.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieappmad23.conversion.Converters
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MovieDao
// :: equivalent zu typeof
@Database(entities =  [Movie::class], version = 4)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var _instance: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase{
            //verhindert dass ein singelton 2 mal erstellt wird
            synchronized(this){
                if (_instance == null){
                    _instance = Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            //not-null assertion, guarantees that _instance value is not null - proramm crashes if null
            return _instance!!
        }
    }
}