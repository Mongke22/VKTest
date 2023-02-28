package com.example.vktest

import android.content.Context
import android.util.AttributeSet
import android.view.View

class VKCustomClocksView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
): View(context, attributeSet, defStyleAttr, defStyleRes) {

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.DefaultMyCustomClockStyle
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.myCustomClockStyle
    )

    constructor(context: Context) : this(context, null)


}
