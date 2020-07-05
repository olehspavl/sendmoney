package com.sendmoney.sendmoney

import com.sendmoney.sendmoney.carddetails.CardDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val loadModule by lazy { loadKoinModules(sendMoneyModule) }
fun loadModule() = loadModule

val sendMoneyModule = module {
    viewModel { SendMoneyViewModel() }
    viewModel { CardDetailsViewModel() }
}