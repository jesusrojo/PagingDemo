@file:Suppress("unused")

package com.jesusrojo.pagingdemo.utils

import android.widget.TextView
import timber.log.Timber

// todo DEBUG
class DebugHelp {

    companion object{
        private const val tag = "##"

        fun l(message: String) {
                Timber.d("$message $tag")
        }

        fun le(message: String) {
                Timber.e("$message $tag")
        }

        fun lt(message: String) {
            Timber.d("$message THREAD: ${Thread.currentThread().name} $tag")
        }

        fun logAndShowInTV(tvDebug: TextView?,
                           message: String) {
            l(message)
            val textUi = message + "\n"
            tvDebug?.append(textUi)
        }
    }
}