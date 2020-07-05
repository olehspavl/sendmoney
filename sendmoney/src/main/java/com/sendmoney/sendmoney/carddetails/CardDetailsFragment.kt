package com.sendmoney.sendmoney.carddetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.SendMoneyViewModel
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyCarddetailsFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsFragment : Fragment(R.layout.feature_sendmoney_carddetails_fragment) {

    private val moduleViewModel by sharedViewModel<SendMoneyViewModel>()
    private val viewModel by viewModel<CardDetailsViewModel>()
    private val binding by viewBinding(FeatureSendmoneyCarddetailsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initUI()
    }

    private fun loadData() = Unit

    private fun initUI() {
        binding.action.setOnClickListener {
            Toast.makeText(requireContext(), "TBD", Toast.LENGTH_SHORT).show()
        }
    }
}