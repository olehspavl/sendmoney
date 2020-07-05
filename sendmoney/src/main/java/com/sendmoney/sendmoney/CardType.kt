package com.sendmoney.sendmoney

import androidx.annotation.DrawableRes
import java.util.regex.Pattern

enum class CardType {
    VISA,
    MASTERCARD,
    OTHER;

    @DrawableRes
    fun toIconRes() = when (this) {
        OTHER -> R.drawable.feature_sendmoney_ic_credit_card_black_24
        VISA -> R.drawable.feature_sendmoney_ic_visa
        MASTERCARD -> R.drawable.feature_sendmoney_ic_mastercard
    }

    companion object {
        private const val REGEX_VISA = "^4[0-9]{6,}\$"
        private const val REGEX_MASTERCARD =
            "^5[1-5][0-9]{5,}|222[1-9][0-9]{3,}|22[3-9][0-9]{4,}|2[3-6][0-9]{5,}|27[01][0-9]{4,}|2720[0-9]{3,}\$"

        fun from(text: CharSequence) = when {
            Pattern.compile(REGEX_VISA).matcher(text).find() -> VISA
            Pattern.compile(REGEX_MASTERCARD).matcher(text).find() -> MASTERCARD
            else -> OTHER
        }
    }
}