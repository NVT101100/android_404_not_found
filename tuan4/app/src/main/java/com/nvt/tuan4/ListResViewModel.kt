package com.nvt.tuan4

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListResViewModel:ViewModel() {
    private var _listOfData = MutableLiveData<List<Restaurants>>()
    val listOfData: LiveData<List<Restaurants>>
        get() = _listOfData

    fun loadData(context: Context) {
        val data = RestaurantData.getData(context)
        _listOfData.postValue(data)
    }
}