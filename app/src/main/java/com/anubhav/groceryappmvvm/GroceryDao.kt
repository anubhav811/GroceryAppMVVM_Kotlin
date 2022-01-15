package com.anubhav.groceryappmvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GroceryItem?)

    @Delete
    fun delete(item: GroceryItem?)

    @Query("SELECT * FROM grocery_items")
    fun allItems(): LiveData<List<GroceryItem>>
}