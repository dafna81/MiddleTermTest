package cohen.dafna.middletermtest.network

import cohen.dafna.middletermtest.*
import cohen.dafna.middletermtest.models.CurrencyResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CurrencyService {
    @GET("NGHJ3UTW")
    suspend fun getAllRates(): CurrencyResponse

    companion object {
        fun create() : CurrencyService {
            val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyService::class.java)
        }
    }
}

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url().newBuilder().build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}