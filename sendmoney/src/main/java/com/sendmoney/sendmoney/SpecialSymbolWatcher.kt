package com.sendmoney.sendmoney

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.SoftReference

abstract class SpecialSymbolWatcher(
    private val symbol: Char,
    private val editText: SoftReference<EditText>,
    private val watchers: SoftReference<List<TextWatcher>> = SoftReference(emptyList())
) : TextWatcher {
    private var pointerPosition = 0
    private var erase = false
        get() {
            val value = field
            field = false
            return value
        }

    abstract fun format(raw: String): String

    override fun afterTextChanged(input: Editable?) {
        var raw = if (erase) {
            input?.substring(0, pointerPosition - 1) + input?.substring(
                pointerPosition
            )
        } else {
            input?.toString() ?: ""
        }
        raw = raw.replace(symbol.toString(), "")

        val formatted = format(raw)

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

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    private fun shouldErase(
        sourceText: CharSequence?,
        pointerPosition: Int,
        newTextLength: Int
    ) = newTextLength == 0 &&
            sourceText?.isNotBlank() == true &&
            sourceText[pointerPosition] == symbol
}