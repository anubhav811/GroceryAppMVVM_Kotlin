package com.anubhav.groceryappmvvm

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTv: TextView
    var priceTv: TextView
    var quantityTv: TextView
    var amountTv: TextView
    var deleteIv: ImageView

    init {
        nameTv = itemView.findViewById(R.id.tv_itemName)
        priceTv = itemView.findViewById(R.id.tv_itemPrice)
        quantityTv = itemView.findViewById(R.id.tv_itemQuantity)
        amountTv = itemView.findViewById(R.id.tv_total)
        deleteIv = itemView.findViewById(R.id.iv_delete)
    }
}