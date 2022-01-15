package com.anubhav.groceryappmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
class GroceryItem(
    @field:ColumnInfo(name = "itemName")
    var itemName: String,
    @field:ColumnInfo(name = "itemQuantity")
    var itemQuantity: Int,
    @field:ColumnInfo(name = "itemPrice")
    var itemPrice: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}