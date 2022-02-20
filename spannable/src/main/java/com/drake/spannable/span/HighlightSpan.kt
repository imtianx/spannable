/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drake.spannable.span

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

class HighlightSpan(
    @ColorInt val color: Int,
    val typeface: Typeface? = null,
    val onClick: ((View) -> Unit)? = null
) : ClickableSpan() {

    constructor(
        color: String,
        typeface: Typeface? = null,
        onClick: ((View) -> Unit)? = null
    ) : this(Color.parseColor(color), typeface, onClick)

    constructor(
        context: Context,
        @ColorRes colorRes: Int,
        typeface: Typeface? = null,
        onClick: ((View) -> Unit)? = null
    ) : this(context.resources.getColor(colorRes), typeface, onClick)

    override fun updateDrawState(ds: TextPaint) {
        ds.color = color
        typeface?.let {
            ds.typeface = typeface
        }
    }

    override fun onClick(widget: View) {
        onClick?.invoke(widget)
    }
}