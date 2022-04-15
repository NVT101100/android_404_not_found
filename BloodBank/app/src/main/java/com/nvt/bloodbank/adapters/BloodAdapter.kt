package com.nvt.bloodbank.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.nvt.bloodbank.R
import com.nvt.bloodbank.databinding.DetailPagerItemBinding
import com.nvt.bloodbank.dto.Blood

class BloodAdapter(var bloods:List<Blood> = listOf(),val context: Context?):PagerAdapter(){
    private lateinit var binding : DetailPagerItemBinding
    override fun getCount(): Int {
        return bloods.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = DetailPagerItemBinding.inflate(LayoutInflater.from(context),container,false)
        binding.bloodDetail = bloods[position]
        container.addView(binding.root)
        return binding.root
    }

}