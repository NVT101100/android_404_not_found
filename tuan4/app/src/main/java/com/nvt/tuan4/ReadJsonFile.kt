package com.nvt.tuan4

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ReadJsonFile {
   fun readFile(context: Context):java.util.ArrayList<Restaurants> {
        var restaurants:ArrayList<Restaurants> = ArrayList<Restaurants>()
        val file :InputStream = context.resources.openRawResource(R.raw.db_restaurant)
        val buffer:BufferedReader = BufferedReader(InputStreamReader(file))
        var n = 0
        do {
            var line = buffer.readLine()
            var jsonObject : JSONObject = JSONObject(line)
            var restaurant = Restaurants(jsonObject.getString("Id").toInt(),jsonObject.getString("Name"),jsonObject.getString("Address")+" "+jsonObject.getString("District"),jsonObject.getString("MobilePicturePath"))
            restaurants.add(restaurant)
            n++
        } while (n<20)
        return restaurants
    }
}