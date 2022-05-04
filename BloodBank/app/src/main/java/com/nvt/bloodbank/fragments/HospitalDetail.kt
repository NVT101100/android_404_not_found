package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nvt.bloodbank.adapters.BloodAdapter
import com.nvt.bloodbank.databinding.HospitalDetailBinding
import com.nvt.bloodbank.dto.Blood

class HospitalDetail:Fragment() {
    private lateinit var binding: HospitalDetailBinding
    private val args : HospitalDetailArgs by navArgs()
    private lateinit var adapter: BloodAdapter
    private lateinit var handler: Handler
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HospitalDetailBinding.inflate(inflater,container,false)
        val details = args.hospitalListBlood
        if (details != null) {
            adapter = BloodAdapter(details.hospitalBlood,context)
        }
        binding.hospitalDetails = details
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = adapter
        handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                var nextPage = binding.bloodDetailPage.currentItem
                if(nextPage == adapter.count-1) nextPage = 0
                else nextPage++
                binding.bloodDetailPage.setCurrentItem(nextPage,true)
                handler.postDelayed(this,3000);
            }
        }
        handler.post(runnable)
    }
}