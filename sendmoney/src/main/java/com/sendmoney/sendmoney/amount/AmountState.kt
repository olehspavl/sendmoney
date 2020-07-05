package com.sendmoney.sendmoney.amount

import androidx.annotation.DrawableRes

data class CardInfo(
    val cardDetails: String,
    @DrawableRes val cardIconRes: Int
)