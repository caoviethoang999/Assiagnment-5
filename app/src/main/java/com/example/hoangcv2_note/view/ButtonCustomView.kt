package com.example.hoangcv2_note.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.hoangcv2_note.R

class ButtonCustomView : FrameLayout {
    var mImageView: ImageView? = null
    var mTitle: TextView? = null
    var resId = 0

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        LayoutInflater.from(context).inflate(R.layout.button_custom_view, this)
        mImageView = findViewById<View>(R.id.imageViewButton) as ImageView
        mTitle = findViewById<View>(R.id.textViewButton) as TextView
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton)
        try {
            resId = a.getResourceId(R.styleable.MyButton_Imagesrc, 0)
            mTitle!!.setText(a.getString(R.styleable.MyButton_Title))
            val color = a.getColor(R.styleable.MyButton_Text_Color, 0)
            mTitle!!.setTextColor(color)

        } finally {
            a.recycle()
        }
        if (resId != 0) {
            mImageView!!.setImageResource(resId)
        }
    }
}