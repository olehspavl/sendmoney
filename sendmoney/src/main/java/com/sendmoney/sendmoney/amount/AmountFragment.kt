package com.sendmoney.sendmoney.amount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyAmountFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding
import java.lang.ref.SoftReference

class AmountFragment : Fragment(R.layout.feature_sendmoney_amount_fragment) {

    private val binding by viewBinding(FeatureSendmoneyAmountFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.action.setOnClickListener { }
        binding.changeCard.setOnClickListener {
            findNavController().navigate(AmountFragmentDirections.toOptions())
        }
        binding.amount.apply {
            addTextChangedListener(AmountDotWatcher(SoftReference(this)))
        }
    }
}