package com.sendmoney.sendmoney.carddetails

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.SoftReference

abstract class SpecialSymbolWatcher(
    private val symbol: Char,
    private val blockSize: Int,
    private val editText: SoftReference<EditText>,
    private val watchers: SoftReference<List<TextWatcher>>
) : TextWatcher {
    private var pointerPosition = 0
    private var erase = false
        get() {
            val value = field
            field = false
            return value
        }

    override fun afterTextChanged(input: Editable?) {
        var formatted = ""
        var raw = if (erase) {
            input?.substring(0, pointerPosition - 1) + input?.substring(
                pointerPosition
            )
        } else {
            input?.toString() ?: ""
        }
        raw = raw.replace(symbol.toString(), "")

        while (raw.length > blockSize) {
            formatted += raw.substring(0 until blockSize) + symbol.toString()
            raw = raw.substring(blockSize)
        }
        formatted += raw

        pointerPosition += input?.let { formatted.length - input.length } ?: 0
        editText.get()?.run {
            removeTextChangedListener(this@SpecialSymbolWatcher)
            watchers.get()?.forEach {
                addTextChangedListener(it)
            }
            setText(formatted)
            setSelection(if (pointerPosition >= 0) pointerPosition else 0)
            addTextChangedListener(this@SpecialSymbolWatcher)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        pointerPosition = start + after
        erase = shouldErase(s, start, after)
        editText.get()?.run {
            watchers.get()?.forEach {
                removeTextChangedListener(it)
            }
        }
    }

    private fun shouldErase(
        sourceText: CharSequence?,
        pointerPosition: Int,
        newTextLength: Int
    ) = newTextLength == 0 &&
            sourceText?.isNotBlank() == true &&
            sourceText[pointerPosition] == symbol

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}

class DateSplashWatcher(
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : SpecialSymbolWatcher(SPECIAL_SYMBOL, BLOCK_SIZE, editText, watchers) {
    companion object {
        private const val SPECIAL_SYMBOL = '/'
        const val specialSymbol = SPECIAL_SYMBOL.toString()
        private const val BLOCK_SIZE = 2
    }
}

class NumberSpaceWatcher(
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : SpecialSymbolWatcher(SPECIAL_SYMBOL, BLOCK_SIZE, editText, watchers) {
    companion object {
        private const val SPECIAL_SYMBOL = ' '
        const val specialSymbol = SPECIAL_SYMBOL.toString()
        private const val BLOCK_SIZE = 4
    }
}
