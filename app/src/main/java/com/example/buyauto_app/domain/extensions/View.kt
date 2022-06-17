package com.example.buyauto_app.domain.extensions

import android.annotation.SuppressLint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.setPasswordToggle() {
    this.setOnTouchListener(View.OnTouchListener { _, event ->
        val drawableEnd = 2
        if (event.rawX >= this.right - this.compoundDrawables[drawableEnd].bounds.width()
        ) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
                MotionEvent.ACTION_UP -> {
                    this.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
            this.setSelection(this.text.length)
            return@OnTouchListener true
        }
        false
    })
}