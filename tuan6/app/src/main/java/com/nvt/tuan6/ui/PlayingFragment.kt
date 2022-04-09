package com.nvt.tuan6.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvt.tuan6.HomeVM
import com.nvt.tuan6.MovieAdapter
import com.nvt.tuan6.R
import com.nvt.tuan6.databinding.FragmentPlayingBinding

class PlayingFragment:Fragment() {
    private lateinit var model : HomeVM
    private lateinit var binding: FragmentPlayingBinding
    private lateinit var adapter : MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayingBinding.inflate(inflater,container,false)
        model = ViewModelProvider(this)[HomeVM::class.java]
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
        registerErrorList()
    }


    override fun onStart() {
        super.onStart()
        model.getNowPlaying()
    }

    private fun setUpMovieList() {
        adapter = MovieAdapter()
        val lm = LinearLayoutManager(context)
        binding.listPlay.layoutManager = lm
        binding.listPlay.adapter = adapter
    }

    private fun registerMovieList() {
        model.movieData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun registerErrorList() {
        model.errEvent.observe(viewLifecycleOwner){
            //show dialog
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_optons,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val lm = LinearLayoutManager(context)
        val grid = GridLayoutManager(context,2)
        Log.d("check","${item.title}")
        when(item.itemId) {
            R.id.list ->{
                Log.d("check","true")
                binding.listPlay.layoutManager = lm
            }
            R.id.grid -> {
                binding.listPlay.layoutManager = grid
            }
        }
        return super.onOptionsItemSelected(item)
    }

}