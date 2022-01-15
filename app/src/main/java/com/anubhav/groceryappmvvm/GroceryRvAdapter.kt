package com.anubhav.groceryappmvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter(
    var groceryList : List<GroceryItem>,
    var groceryItemClickInterface: GroceryItemClickInterface
): RecyclerView.Adapter<GroceryViewHolder>() {


    interface GroceryItemClickInterface {
        fun onItemClick(groceryItem: GroceryItem?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val currentItem = groceryList[position]
        holder.nameTv.text = currentItem.itemName
        holder.priceTv.text = "Rs. " + currentItem.itemPrice
        holder.quantityTv.text = currentItem.itemQuantity.toString()
        val itemTotal = currentItem.itemPrice * currentItem.itemPrice
        holder.amountTv.text = "Rs. $itemTotal"
        holder.deleteIv.setOnClickListener {
            try {
                groceryItemClickInterface!!.onItemClick(currentItem)
            } catch (e: Exception) {
                Log.d("deleted", e.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

}

