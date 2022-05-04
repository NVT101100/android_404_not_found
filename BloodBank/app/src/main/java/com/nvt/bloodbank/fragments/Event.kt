package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvt.bloodbank.adapters.EventAdapter
import com.nvt.bloodbank.databinding.EventBinding
import com.nvt.bloodbank.models.EventModel

class Event:Fragment() {
    private lateinit var binding :EventBinding
    private lateinit var eventAdapter: EventAdapter
    private lateinit var model : EventModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventBinding.inflate(inflater,container,false)
        eventAdapter = EventAdapter()
        model = EventModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.Init()
        model.listEvent.observe(viewLifecycleOwner, Observer {
            eventAdapter.submitList(it)
        })
        val lm = LinearLayoutManager(context)
        binding.listEvent.layoutManager = lm
        binding.listEvent.adapter = eventAdapter
        model.userState.observe(viewLifecycleOwner) {

        }
    }

}