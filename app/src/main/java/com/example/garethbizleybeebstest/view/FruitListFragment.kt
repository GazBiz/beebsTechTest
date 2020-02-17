package com.example.garethbizleybeebstest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.viewmodel.FruitEvent
import com.example.garethbizleybeebstest.viewmodel.FruitListViewModel
import com.example.garethbizleybeebstest.viewmodel.viewModel
import kotlinx.android.synthetic.main.fruit_list_fragment_layout.*
import javax.inject.Inject

class FruitListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModel<FruitListViewModel> { viewModelFactory }

    private lateinit var adapter: FruitRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadFruits()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fruit_list_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FruitRecyclerViewAdapter(
            viewModel.fruitsList,
            this.findNavController()
        )

        fruitsRecyclerView.layoutManager = LinearLayoutManager(context)
        fruitsRecyclerView.adapter = adapter

        fruitRefreshLayout.setOnRefreshListener(this)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.event.observe(this, Observer<FruitEvent> {event ->
            when (event) {
                FruitEvent.LoadSuccess -> {
                    adapter.notifyDataSetChanged()
                    fruitRefreshLayout.isRefreshing = false
                }
                is FruitEvent.LogEvent -> {
                    logEvent(event.event, event.data)
                }
                else -> {
                    Log.d(this.toString(), "Unknown fruit event: $event")
                }
            }
        }
        )
    }

    override fun onRefresh() {
        viewModel.loadFruits()
    }
}