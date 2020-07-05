package com.sendmoney.sendmoney.carddetails

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.SendMoneyViewModel
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyCarddetailsFragmentBinding
import com.sendmoney.sendmoney.loadModule
import com.sendmoney.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.SoftReference

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
        initObservers()
    }

    private fun initObservers() {
        viewModel.cardNumberState.observe(viewLifecycleOwner, Observer {
            when (it) {
                EditTextState.Neutral -> binding.cardNumberLayout.error = ""
                is EditTextState.Error -> binding.cardNumberLayout.error =
                    getString(it.messageRes)
            }
        })
        viewModel.cardDateState.observe(viewLifecycleOwner, Observer {
            when (it) {
                EditTextState.Neutral -> binding.cardDateLayout.error = ""
                is EditTextState.Error -> binding.cardDateLayout.error =
                    getString(it.messageRes)
            }
        })
        viewModel.cardCvvState.observe(viewLifecycleOwner, Observer {
            when (it) {
                EditTextState.Neutral -> binding.cardCvvLayout.error = ""
                is EditTextState.Error -> binding.cardCvvLayout.error =
                    getString(it.messageRes)
            }
        })
    }

    private fun loadData() = Unit

    private fun initUI() {
        binding.action.setOnClickListener {
            viewModel.validateCardNumber(binding.cardNumber.text.toString())
            viewModel.validateCardDate(binding.cardDate.text.toString())
            viewModel.validateCardCvv(binding.cardCvv.text.toString())
        }
        val numberValidation = binding.cardNumber.doAfterTextChanged { text ->
            viewModel.onCardNumberChanged(text)
        }
        binding.cardNumber.apply {
            addTextChangedListener(
                NumberSpaceWatcher(
                    SoftReference(this),
                    SoftReference(listOf(numberValidation))
                )
            )
        }
        val dateValidation = binding.cardDate.doAfterTextChanged { text ->
            viewModel.onCardDateChanged(text)
        }
        binding.cardDate.apply {
            addTextChangedListener(
                DateSplashWatcher(
                    SoftReference(this),
                    SoftReference(listOf(dateValidation))
                )
            )
        }
    }
}