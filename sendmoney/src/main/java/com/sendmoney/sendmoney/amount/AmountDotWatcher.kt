package com.sendmoney.sendmoney.amount

import android.text.TextWatcher
import android.widget.EditText
import com.sendmoney.sendmoney.SpecialSymbolWatcher
import java.lang.ref.SoftReference

class AmountDotWatcher(
    editText: SoftReference<EditText>,
    watchers: SoftReference<List<TextWatcher>>
) : SpecialSymbolWatcher(SPECIAL_SYMBOL, editText, watchers) {
    override fun format(raw: String): String {
        val sb = StringBuilder(raw)
        if (raw.length < 3) {
            for (i in 0 until (3 - raw.length)) {
                sb.insert(0, '0')
            }
        } else if (raw.length > 3) {
            run loop@{
                for (i in 0 until raw.length - 3) {
                    if (raw[i] == '0') {
                        sb.deleteCharAt(0)
                    } else {
                        return@loop
                    }
                }
            }
        }
        sb.insert(sb.length - 2, SPECIAL_SYMBOL)
        return sb.toString()
    }
    companion object {
        private const val SPECIAL_SYMBOL = '.'
    }
}
