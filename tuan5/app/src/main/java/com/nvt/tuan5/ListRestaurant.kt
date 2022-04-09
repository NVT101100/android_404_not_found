package com.nvt.tuan5

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nvt.tuan5.databinding.ActivityListRestaurantBinding

class ListRestaurant : Fragment() {

private lateinit var binding: ActivityListRestaurantBinding
private var resAdapter: ResAdapter = ResAdapter()
    private var model : ListResViewModel = ListResViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityListRestaurantBinding.inflate(inflater,container,false)
        val listRestaurant = binding.listRestaurant
        model.loadData(context)
        listRestaurant.adapter = resAdapter
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = RecyclerView.VERTICAL
        listRestaurant.layoutManager = linearLayout
        Log.d("check",resAdapter.toString())
        model.listOfData.observe(viewLifecycleOwner, Observer<List<Restaurants>> {
            resAdapter.submitList(it)
        })

        return binding.root
    }
}