package com.example.garethbizleybeebstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.garethbizleybeebstest.Constants.Companion.DISPLAY
import com.example.garethbizleybeebstest.Constants.Companion.FRUIT_KEY
import com.example.garethbizleybeebstest.Constants.Companion.START_TIME_KEY
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.databinding.SingleFruitFragmentLayoutBinding
import com.example.garethbizleybeebstest.model.FruitItem
import com.example.garethbizleybeebstest.viewmodel.SingleFruitViewModel
import com.example.garethbizleybeebstest.viewmodel.viewModel
import kotlinx.android.synthetic.main.single_fruit_fragment_layout.*
import javax.inject.Inject

class SingleFruitFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModel<SingleFruitViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<SingleFruitFragmentLayoutBinding>(
            inflater,
            R.layout.single_fruit_fragment_layout,
            container,
            false
        ).apply {
            this.viewModel = this@SingleFruitFragment.viewModel
            this.lifecycleOwner = this@SingleFruitFragment
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logDisplayTime()
        setupToolbar()
        arguments?.getParcelable<FruitItem>(FRUIT_KEY)?.let { fruitItem ->
            viewModel.setViewData(fruitItem)
        }
    }

    private fun setupToolbar() {
        singleFruitToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        singleFruitToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    }

    private fun logDisplayTime() {
        arguments?.getLong(START_TIME_KEY)?.let { startTime ->
            val endTime = System.currentTimeMillis()
            val displayTime = (endTime - startTime).toString()
            logEvent(DISPLAY, displayTime)
        }
    }
}