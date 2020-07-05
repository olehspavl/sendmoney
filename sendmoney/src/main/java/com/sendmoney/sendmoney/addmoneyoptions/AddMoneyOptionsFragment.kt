package com.sendmoney.sendmoney.addmoneyoptions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyAddmoneyoptionsFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding

class AddMoneyOptionsFragment : Fragment(R.layout.feature_sendmoney_addmoneyoptions_fragment) {
    private val binding by viewBinding(FeatureSendmoneyAddmoneyoptionsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.debitCreditCard.setOnClickListener {
            findNavController().navigate(AddMoneyOptionsFragmentDirections.toCarddetails())
        }
    }
}