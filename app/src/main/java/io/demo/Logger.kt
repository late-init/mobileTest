package io.demo

import android.util.Log

private const val TAG = "MobileTest"

fun log(content: String) {
  Log.d(TAG, content)
}

fun errorMsg(message: String) {
  Log.e(TAG, message)
}