package cohen.dafna.middletermtest.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CurrencyRepository {
    companion object {
        suspend fun getAllCurrencies(): Map<String,Double> {
            return withContext(Dispatchers.IO) {
                try {
                    CurrencyService.create().getAllRates().rates
                } catch (e: Exception) {
                    throw mapExceptions(e)
                }
            }
        }
    }
}

sealed class CurrencyApiException : Exception() {
    class NoNetworkException : CurrencyApiException()
    class HttpException : CurrencyApiException()
    class UnexpectedException : CurrencyApiException()
}

fun mapExceptions(e: Exception): Exception {
    return when (e) {
        is IOException -> CurrencyApiException.NoNetworkException()
        is HttpException -> CurrencyApiException.HttpException()
        else -> CurrencyApiException.UnexpectedException()
    }
}