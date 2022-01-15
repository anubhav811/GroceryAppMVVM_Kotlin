package com.anubhav.groceryappmvvm

import androidx.lifecycle.LiveData

class GroceryRepository(private val groceryDatabase: GroceryDatabase){

    fun insert(item: GroceryItem?) {
        groceryDatabase.groceryDao().insert(item)
    }

    fun delete(item: GroceryItem?) {
        groceryDatabase.groceryDao().delete(item)
    }

    fun getItems() : LiveData<List<GroceryItem>> {
        return groceryDatabase.groceryDao().allItems()
    }
}