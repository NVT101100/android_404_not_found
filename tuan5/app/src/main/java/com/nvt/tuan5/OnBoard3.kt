package com.nvt.tuan5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class OnBoard3:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return return inflater.inflate(R.layout.onboard3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = object:Runnable {
            override fun run() {
                controller.navigate(R.id.board3_to_welcome)
            }
        }
        handler.postDelayed(runnable,4000)
    }
}