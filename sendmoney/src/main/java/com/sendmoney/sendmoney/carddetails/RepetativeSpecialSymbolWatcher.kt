package com.sendmoney.sendmoney.carddetails

import android.text.TextWatcher
import android.widget.EditText
import com.sendmoney.sendmoney.SpecialSymbolWatcher
import java.lang.ref.SoftReference

abstract class RepetitiveSpecialSymbolWatcher(
    private val symbol: Char,
    private val blockSize: Int,
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : SpecialSymbolWatcher(symbol, editText, watchers) {
    override fun format(raw: String): String {
        var rawCopy = raw
        var formatted = ""
        while (rawCopy.length > blockSize) {
            formatted += rawCopy.substring(0 until blockSize) + symbol.toString()
            rawCopy = rawCopy.substring(blockSize)
        }
        formatted += rawCopy
        return formatted
    }
}

class DateSplashWatcher(
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : RepetitiveSpecialSymbolWatcher(SPECIAL_SYMBOL, BLOCK_SIZE, editText, watchers) {
    companion object {
        private const val SPECIAL_SYMBOL = '/'
        const val specialSymbol = SPECIAL_SYMBOL.toString()
        private const val BLOCK_SIZE = 2
    }
}

class NumberSpaceWatcher(
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : RepetitiveSpecialSymbolWatcher(SPECIAL_SYMBOL, BLOCK_SIZE, editText, watchers) {
    companion object {
        private const val SPECIAL_SYMBOL = ' '
        const val specialSymbol = SPECIAL_SYMBOL.toString()
        private const val BLOCK_SIZE = 4
    }
}
