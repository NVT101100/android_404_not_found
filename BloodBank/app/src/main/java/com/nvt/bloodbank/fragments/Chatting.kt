package com.nvt.bloodbank.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.adapters.ChatAdapter
import com.nvt.bloodbank.databinding.ChattingBinding
import com.nvt.bloodbank.dto.Chats
import com.nvt.bloodbank.models.ChatModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class Chatting : Fragment() {
    private val args: ChattingArgs by navArgs()
    private lateinit var binding: ChattingBinding
    private lateinit var model: ChatModel
    private lateinit var adapter: ChatAdapter
    private val database = Firebase.database(Constants.databaseURL).reference
    private val auth = Firebase.auth.currentUser
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChattingBinding.inflate(inflater, container, false)
        model = ChatModel()
        adapter = ChatAdapter()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactDetail = args.chatWith
        val lm = LinearLayoutManager(context)
        binding.contactDetail = contactDetail
        model.initHospId(contactDetail?.hospId.toString())
        model.init()
        model.listMsg.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.listMsg.adapter = adapter
            binding.listMsg.layoutManager = lm
        }
        database.child("contacts/${auth?.uid}").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                model.init()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        binding.btnSend.setOnClickListener {
            val msg = binding.inputMsg.text.toString()
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            val dateArr = formatted.split(" ")
            var msgDto = Chats(msg, auth?.uid, dateArr[0], dateArr[1])
            database.child("contacts/${auth?.uid}/${contactDetail?.hospId}")
                .child(model.msgCount.value.toString()).setValue(msgDto).addOnSuccessListener {
                binding.inputMsg.setText("")
            }
        }
    }
}