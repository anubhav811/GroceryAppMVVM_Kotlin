package com.anubhav.groceryappmvvm

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [GroceryItem::class], version = 1)
abstract class GroceryDatabase() : RoomDatabase() {

    abstract fun groceryDao(): GroceryDao


    companion object {
        @Volatile
        private var instance: GroceryDatabase? = null
        private val LOCK  = Any()
        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(LOCK){
            instance?: createDatabase(context).also {
                instance = it
            }
            }

    private fun createDatabase(context: Context)=
        Room.databaseBuilder(
            context.applicationContext,
            GroceryDatabase::class.java,
            "GroceryDB")
            .build()

}
}