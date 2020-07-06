package com.sendmoney.sendmoney.carddetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sendmoney.sendmoney.CardType
import com.sendmoney.sendmoney.R
import java.util.*

class CardDetailsViewModel : ViewModel() {

    val cardNumberState = MutableLiveData<CardNumberState>()
    val cardDateState = MutableLiveData<EditTextState>()
    val cardCvvState = MutableLiveData<EditTextState>()
    val screenState = MutableLiveData<CardDetailsState>()

    fun onCardNumberChanged(text: CharSequence?) {
        if (text?.length == CARD_NUMBER_LENGTH) validateCardNumber(text)
    }

    fun onCardDateChanged(text: CharSequence?) {
        if (text?.length == CARD_DATE_LENGTH) validateCardDate(text)
    }

    fun onCardCvvChanged(text: CharSequence?) {
        if (text?.length == CARD_CVV_LENGTH) validateCardCvv(text)
    }

    fun validateAll(number: String, date: String, cvv: String) {
        validateCardNumber(number)
        validateCardDate(date)
        validateCardCvv(cvv)
        val isNumberNotValid = cardNumberState.value is CardNumberState.Error
        val isDateNotValid = cardDateState.value is EditTextState.Error
        val isCvvNotValid = cardCvvState.value is EditTextState.Error
        if (!isNumberNotValid && !isDateNotValid && !isCvvNotValid) {
            screenState.value = CardDetailsState.NextScreen
        }
    }

    private fun validateCardNumber(text: CharSequence) {
        cardNumberState.value = if (text.length == CARD_NUMBER_LENGTH) {
            val cardType = CardType.from(text)
            CardNumberState.Neutral(cardType.toIconRes())
        } else {
            CardNumberState.Error(R.string.feature_sendmoney_card_number_error)
        }
    }

    private fun validateCardCvv(text: CharSequence) {
        cardCvvState.value =
            if (text.length == CARD_CVV_LENGTH) EditTextState.Neutral
            else EditTextState.Error(R.string.feature_sendmoney_card_cvv_error)
    }

    private fun validateCardDate(text: CharSequence) {
        var valid = false
        if (text.length == CARD_DATE_LENGTH) {
            val rawText = text.toString().replace(DateSplashWatcher.specialSymbol, "")
            val currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100

            val month = rawText.substring(0..1).toInt()
            val year = rawText.substring(2).toInt()

            valid = (month in 1..12) and (year >= currentYear)
            if (valid && year == currentYear) {
                val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
                valid = valid and (month > currentMonth)
            }
        }

        cardDateState.value =
            if (valid) EditTextState.Neutral
            else EditTextState.Error(R.string.feature_sendmoney_card_date_error)
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 19
        private const val CARD_CVV_LENGTH = 3
        private const val CARD_DATE_LENGTH = 5
    }
}