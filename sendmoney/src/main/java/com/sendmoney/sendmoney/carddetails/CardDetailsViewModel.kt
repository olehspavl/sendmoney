package com.sendmoney.sendmoney.carddetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sendmoney.sendmoney.R

class CardDetailsViewModel : ViewModel() {

    val cardNumberState = MutableLiveData<EditTextState>()

    fun onCardNumberChanged(text: CharSequence?) {
        if (text?.length == CARD_NUMBER_LENGTH) validateCardNumber(text)
    }

    fun validateCardNumber(text: CharSequence) {
        cardNumberState.value =
            if (text.length == CARD_NUMBER_LENGTH) EditTextState.Neutral
            else EditTextState.Error(R.string.feature_sendmoney_card_number_error)
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 19
    }
}