package com.sendmoney.sendmoney.carddetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.SendMoneyViewModel
import com.sendmoney.sendmoney.loadModule
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsFragment : Fragment(R.layout.feature_sendmoney_carddetails_fragment) {

    private val moduleViewModel by sharedViewModel<SendMoneyViewModel>()
    private val viewModel by viewModel<CardDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() = Unit
}