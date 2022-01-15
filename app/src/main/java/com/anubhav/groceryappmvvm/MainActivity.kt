package com.anubhav.groceryappmvvm

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , GroceryRVAdapter.GroceryItemClickInterface {
    private lateinit var groceryRVAdapter : GroceryRVAdapter
    private lateinit var groceryViewModel: GroceryViewModel
    private lateinit var groceryList: List<GroceryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.addFAB)

        groceryList  = ArrayList()

        GroceryRVAdapter(groceryList,this).also { it.also { groceryRVAdapter = it } }

        recyclerView.adapter = groceryRVAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val repository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(repository)
        groceryViewModel = ViewModelProvider(this,factory)[GroceryViewModel::class.java]

        groceryViewModel.allItems().observe(this , {
            groceryRVAdapter.groceryList=it
            groceryRVAdapter.notifyDataSetChanged()
        })

        floatingActionButton.setOnClickListener { openDialog() }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.btnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.btnAdd)
        val etItemName = dialog.findViewById<EditText>(R.id.etItemName)
        val etItemPrice = dialog.findViewById<EditText>(R.id.etItemPrice)
        val etItemQuantity = dialog.findViewById<EditText>(R.id.etItemQuantity)
        cancelBtn.setOnClickListener { dialog.dismiss() }
        addBtn.setOnClickListener {
            val itemName = etItemName.text.toString()
            val itemPrice = etItemPrice.text.toString()
            val itemQuantity = etItemQuantity.text.toString()
            val qty = Integer.valueOf(itemQuantity)
            val price = Integer.valueOf(itemPrice)
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                val item = GroceryItem(itemName, qty, price)
                groceryViewModel.insert(item)
                Toast.makeText(applicationContext, "Item Inserted..", Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "Fill all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(groceryItem: GroceryItem?) {
        if (groceryItem != null) groceryViewModel.delete(groceryItem)
        groceryRVAdapter.notifyDataSetChanged()    }

}

