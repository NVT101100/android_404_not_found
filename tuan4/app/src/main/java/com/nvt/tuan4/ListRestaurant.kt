package com.nvt.tuan4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvt.tuan4.databinding.ActivityListRestaurantBinding

class ListRestaurant : AppCompatActivity() {

private lateinit var binding: ActivityListRestaurantBinding
private var resAdapter: ResAdapter = ResAdapter(this)
    private var model : ListResViewModel = ListResViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_restaurant)
        val listRestaurant = findViewById<RecyclerView>(R.id.listRestaurant)
        model.loadData(this)
        listRestaurant.adapter = resAdapter
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = RecyclerView.VERTICAL
        listRestaurant.layoutManager = linearLayout
        Log.d("check",resAdapter.toString())
        model.listOfData.observe(this, Observer<List<Restaurants>> {
            resAdapter.submitList(it)
        })
    }
}