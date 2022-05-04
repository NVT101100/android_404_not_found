package com.nvt.bloodbank.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.activities.MainActivity
import com.nvt.bloodbank.databinding.EventItemBinding
import com.nvt.bloodbank.dto.Certificates
import com.nvt.bloodbank.dto.Events
import com.nvt.bloodbank.fragments.EventDirections
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventAdapter:ListAdapter<Events,EventAdapter.EventViewHolder>(EventDiffUtilCallback()) {
    class EventDiffUtilCallback : DiffUtil.ItemCallback<Events>() {
        override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem == newItem
        }
    }
    class EventViewHolder private constructor(var binding:EventItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        private val auth = Firebase.auth.currentUser
        private val database = Firebase.database(Constants.databaseURL).reference
        companion object {
            fun from(parent: ViewGroup): EventViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventItemBinding.inflate(layoutInflater, parent, false)
                return EventViewHolder(binding)
            }
        }
            @RequiresApi(Build.VERSION_CODES.O)
            fun binding(item:Events) {
                var lastDate = ""
                database.child("users/${auth?.uid}/certs").addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val numCert = snapshot.childrenCount
                        lastDate = snapshot.child("${numCert-1}/date").getValue<String>().toString()
                        binding.eventObject = item
                        binding.canReg = calculateTime(item.eventDay.toString(),lastDate)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        print("failed")
                    }

                })
            }
        @RequiresApi(Build.VERSION_CODES.O)
        private fun calculateTime(date1:String,date2:String): Boolean{
            val eventDate = date1.split("/")
            val lastDate = date2.split("/")
            Log.d("check","$eventDate/$lastDate")
            return (lastDate[2].toInt() - eventDate[2].toInt() < 0) ||
                    (eventDate[1].toInt() - lastDate[1].toInt() > 4)
        }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
        holder.binding.eventItem.setOnClickListener {
            val mainAct = holder.itemView.context as MainActivity
            val controller = Navigation.findNavController(mainAct.findViewById(R.id.nav_host))
            val action = EventDirections.eventToMap(item)
            controller.navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.from(parent)
    }
}