package com.itis.joke.android.ui.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showShortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
