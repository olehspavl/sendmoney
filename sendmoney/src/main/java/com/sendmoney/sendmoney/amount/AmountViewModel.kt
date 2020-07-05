package com.sendmoney.sendmoney.amount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sendmoney.sendmoney.CardType

class AmountViewModel : ViewModel() {

    val cardInfoState = MutableLiveData<CardInfo>()
    val actionAvailable = MutableLiveData<Boolean>()

    var cardNumber: String? = null
        set(value) {
            value?.let {
                val type = CardType.from(value)
                cardInfoState.value = CardInfo(
                    "${type.name} *${value.takeLast(3)}",
                    type.toIconRes()
                )
            }
        }

    var amount: String? = null
        set(value) {
            try {
                val amount = value?.toFloat() ?: -1F
                actionAvailable.value = if (amount.compareTo(0.0F) > 0) {
                    field = value
                    true
                } else {
                    field = null
                    false
                }
            } catch (e: NumberFormatException) {
                actionAvailable.value = false
            }
        }
}