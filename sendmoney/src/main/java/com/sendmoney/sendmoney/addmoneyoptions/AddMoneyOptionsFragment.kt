package com.sendmoney.sendmoney.addmoneyoptions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyAddmoneyoptionsFragmentBinding

class AddMoneyOptionsFragment : Fragment(R.layout.feature_sendmoney_addmoneyoptions_fragment) {
    private lateinit var binding: FeatureSendmoneyAddmoneyoptionsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeatureSendmoneyAddmoneyoptionsFragmentBinding.inflate(layoutInflater)
        view.findViewById<View>(R.id.debitCreditCard).setOnClickListener {
            findNavController().navigate(AddMoneyOptionsFragmentDirections.toCarddetails())
        }
        initUI()
    }

    private fun initUI() {
//        binding.debitCreditCard.setOnClickListener {
//            findNavController().navigate()
//        }
    }
}