package com.sendmoney.sendmoney.carddetails

import androidx.annotation.StringRes

sealed class EditTextState {
    object Neutral : EditTextState()
    data class Error(@StringRes val messageRes: Int) : EditTextState()
}