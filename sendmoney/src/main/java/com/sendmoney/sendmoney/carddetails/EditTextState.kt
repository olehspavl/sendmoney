package com.sendmoney.sendmoney.carddetails

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.regex.Pattern

sealed class EditTextState {
    object Neutral : EditTextState()
    data class Error(@StringRes val messageRes: Int) : EditTextState()
}

sealed class CardNumberState : EditTextState() {
    data class Neutral(@DrawableRes val typeIconRes: Int) : CardNumberState()
    data class Error(@StringRes val messageRes: Int) : CardNumberState()
}

enum class CardType {
    VISA,
    MASTERCARD,
    OTHER;

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