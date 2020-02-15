package com.example.garethbizleybeebstest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.viewmodel.FruitEvent
import com.example.garethbizleybeebstest.viewmodel.FruitListViewModel
import kotlinx.android.synthetic.main.single_fruit_fragment_layout.*
import kotlinx.android.synthetic.main.fruit_list_fragment_layout.*

class FruitListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var viewModel: FruitListViewModel

    private lateinit var adapter: FruitRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FruitListViewModel::class.java)
        adapter = FruitRecyclerViewAdapter(
            viewModel.fruitsList,
            this.findNavController()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fruit_list_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fruitsRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        fruitRefreshLayout.setOnRefreshListener(this)
        observeEvents()
        viewModel.loadFruits()
    }

    private fun observeEvents() {
        viewModel.event.observe(this, Observer<FruitEvent> {
            when (it) {
                FruitEvent.LOAD_SUCCESS -> {
                    adapter.notifyDataSetChanged()
                    fruitRefreshLayout.isRefreshing = false
                }
                else -> {
                    Log.d(this.toString(), "Unknown fruit event: " + it.name)
                }
            }
        }
        )
    }

    override fun onRefresh() {
        viewModel.loadFruits()
    }
}