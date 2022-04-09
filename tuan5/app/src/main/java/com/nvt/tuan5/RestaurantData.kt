package com.nvt.tuan5

import android.content.Context

object RestaurantData {
    private var res : ReadJsonFile = ReadJsonFile()
    fun getData(context:Context?):ArrayList<Restaurants>{
        return res.readFile(context)
    }
}