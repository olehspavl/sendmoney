package com.sendmoney.sendmoney.carddetails

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class EditTextState {
    object Neutral : EditTextState()
    data class Error(@StringRes val messageRes: Int) : EditTextState()
}

sealed class CardNumberState : EditTextState() {
    data class Neutral(@DrawableRes val typeIconRes: Int) : CardNumberState()
    data class Error(@StringRes val messageRes: Int) : CardNumberState()
}