package cohen.dafna.middletermtest

import android.util.Log

class Logger {
    companion object {
        fun logHandledException(e: Exception) {
            e.localizedMessage?.let { Log.e("Error", it) }
        }
        fun logInfo(message: String) {
            Log.v("Info", message)
        }
    }
}