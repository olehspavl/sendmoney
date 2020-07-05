package com.sendmoney.sendmoney.carddetails

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.SoftReference

class SpaceWatcher(private val editText: SoftReference<EditText>) : TextWatcher {
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
        raw = raw.replace(" ", "")

        while (raw.length > 4) {
            formatted += raw.substring(0..3) + " "
            raw = raw.substring(4)
        }
        formatted += raw

        pointerPosition += input?.let { formatted.length - input.length } ?: 0
        editText.get()?.run {
            removeTextChangedListener(this@SpaceWatcher)
            setText(formatted)
            setSelection(pointerPosition)
            addTextChangedListener(this@SpaceWatcher)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        pointerPosition = start + after
        erase = shouldErase(s, start, after)
    }

    private fun shouldErase(
        sourceText: CharSequence?,
        pointerPosition: Int,
        newTextLength: Int
    ) = newTextLength == 0 &&
            sourceText?.isNotBlank() == true &&
            sourceText[pointerPosition] == ' '

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}