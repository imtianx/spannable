package com.drake.spannable.edit

import android.text.Selection
import android.text.Spannable
import com.drake.spannable.span.HighlightSpan

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author imtianx
 * @date 2023/2/6 12:02
 */
class KeyCodeDeleteHelper private constructor() {

  companion object {
    // delete HighlightSpan tag 
    fun onDelDownForTag(text: Spannable): Boolean {
      val selectionStart = Selection.getSelectionStart(text)
      val selectionEnd = Selection.getSelectionEnd(text)
      text.getSpans(selectionStart, selectionEnd, HighlightSpan::class.java)
        .firstOrNull { text.getSpanEnd(it) == selectionStart }?.run {
          return (selectionStart == selectionEnd).also {
            val spanStart = text.getSpanStart(this)
            val spanEnd = text.getSpanEnd(this)
            Selection.setSelection(text, spanStart, spanEnd)
          }
        }
      return false
    }
  }
}