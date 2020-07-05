package com.sendmoney.sendmoney

import androidx.lifecycle.ViewModel

class SendMoneyViewModel : ViewModel() {
    var cardNumber: String? = null
    var cardDate: String? = null

    var cardCvv: String?
        get() = _cardCvv.toString(Charsets.UTF_8)
        set(value) {
            val byteArray = value?.toByteArray() ?: byteArrayOf()
            if (_cardCvv.contentEquals(byteArray).not()) {
                _cardCvv = byteArray
            }
        }
    private var _cardCvv: ByteArray = byteArrayOf()
}