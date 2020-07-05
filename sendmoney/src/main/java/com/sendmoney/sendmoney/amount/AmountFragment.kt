package com.sendmoney.sendmoney.amount

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.SendMoneyViewModel
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyAmountFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.SoftReference

class AmountFragment : Fragment(R.layout.feature_sendmoney_amount_fragment) {

    private val moduleViewModel by sharedViewModel<SendMoneyViewModel>()
    private val viewModel by viewModel<AmountViewModel>()
    private val binding by viewBinding(FeatureSendmoneyAmountFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
        initEvents()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initUI()
        initObservers()
    }

    private fun loadData() {
        viewModel.cardNumber = moduleViewModel.cardNumber
    }

    private fun initEvents() {
        viewModel.actionAvailable.observe(this, Observer {
            binding.action.isEnabled = it
        })
    }

    private fun initObservers() {
        viewModel.cardInfoState.observe(viewLifecycleOwner, Observer {
            binding.cardIcon.setImageResource(it.cardIconRes)
            binding.cardDetails.text = it.cardDetails
        })
    }

    private fun initUI() {
        binding.action.setOnClickListener {
            moduleViewModel.amount = viewModel.amount
            findNavController().navigate(AmountFragmentDirections.toVerification())
        }
        binding.changeCard.setOnClickListener {
            findNavController().navigate(AmountFragmentDirections.toOptions())
        }
        val amountWatcher = binding.amount.doAfterTextChanged {
            viewModel.amount = it?.toString()
        }
        binding.amount.apply {
            addTextChangedListener(
                AmountDotWatcher(
                    SoftReference(this),
                    SoftReference(listOf(amountWatcher))
                )
            )
        }
    }
}