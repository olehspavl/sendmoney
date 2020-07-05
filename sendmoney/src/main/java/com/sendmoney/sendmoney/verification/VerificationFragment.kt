package com.sendmoney.sendmoney.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyVerificationFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding

class VerificationFragment : Fragment(R.layout.feature_sendmoney_verification_fragment) {

    private val binding by viewBinding(FeatureSendmoneyVerificationFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.action.setOnClickListener {
            findNavController().navigate(VerificationFragmentDirections.toSuccess())
        }
    }
}