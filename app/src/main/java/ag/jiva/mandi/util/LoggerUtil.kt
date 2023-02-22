package ag.jiva.mandi.util

import ag.jiva.mandi.BuildConfig
import android.util.Log


const val TAG = "MandiLogs"

fun printDebugLog(message: String) {

    if (BuildConfig.DEBUG) {
        Log.d(
            TAG,
            "$message   name: ${Thread.currentThread().name} , id: ${Thread.currentThread().id}"
        )
    }
}

fun printDebugLog(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun printErrorLog(message: String) {

    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}
