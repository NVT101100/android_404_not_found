package com.nvt.bloodbank.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.adapters.HospitalAdapter
import com.nvt.bloodbank.databinding.ActivitySearchBinding
import com.nvt.bloodbank.models.HospitalModel

class SearchFragment:Fragment() {
    private lateinit var adapter : HospitalAdapter
    private lateinit var binding : ActivitySearchBinding
    private lateinit var model : HospitalModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySearchBinding.inflate(inflater,container,false)
        adapter = HospitalAdapter()
        model = HospitalModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.Init()
        binding.searchContent.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            @SuppressLint("RestrictedApi")
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    model.search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                model.resetList()
                return true
            }
        })
        model.listHospital.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        val lm = LinearLayoutManager(context)
        binding.listSearch.layoutManager = lm
        binding.listSearch.adapter = adapter
    }

}
