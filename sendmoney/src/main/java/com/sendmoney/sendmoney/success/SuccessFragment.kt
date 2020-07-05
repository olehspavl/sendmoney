package com.sendmoney.sendmoney.success

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.SendMoneyViewModel
import com.sendmoney.sendmoney.databinding.FeatureSendmoneySuccessFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SuccessFragment : Fragment(R.layout.feature_sendmoney_success_fragment) {

    private val moduleViewModel by sharedViewModel<SendMoneyViewModel>()
    private val binding by viewBinding(FeatureSendmoneySuccessFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initUI()
    }

    private fun loadData() {
        binding.cardAmount.text = moduleViewModel.amount
        binding.cardNumber.text = moduleViewModel.cardNumber
        binding.cardCvv.text = moduleViewModel.cardCvv
        binding.cardDate.text = moduleViewModel.cardDate
    }

    private fun initUI() {
        binding.action.setOnClickListener {
            findNavController().navigate(SuccessFragmentDirections.toMain())
        }
    }
}