package cohen.dafna.middletermtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cohen.dafna.middletermtest.*
import cohen.dafna.middletermtest.adapters.CurrencyAdapter
import cohen.dafna.middletermtest.models.Coin
import cohen.dafna.middletermtest.network.CurrencyApiException
import cohen.dafna.middletermtest.network.CurrencyRepository
import cohen.dafna.middletermtest.ui.addImage
import cohen.dafna.middletermtest.ui.flipCard
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>> get() = _rates

    private var _coinsLiveData: MutableLiveData<MutableList<Coin>> = MutableLiveData()
    val coinsLiveData: LiveData<MutableList<Coin>> get() = _coinsLiveData

    private fun convertToShekels(valueInForeignCurrency: Double): Double {
        var result = 0.0
        rates.value?.forEach { rate ->
            if (rate.key == ILS) {
                result = 1 / valueInForeignCurrency * rate.value
            }
        }
        return result
    }

    private fun isHigherThanDollar(valueInForeignCurrency: Double): Boolean {
        rates.value?.forEach { rate ->
            if (rate.key == USD) {
                if (valueInForeignCurrency > rate.value) return true
            }
        }
        return false
    }

    init {
        val coins: MutableList<Coin> = mutableListOf()
        viewModelScope.launch(exceptionHandler()) {
            _rates.value = CurrencyRepository.getAllCurrencies()
            val map = rates.value
            if (map != null) {
                val values = map.values
                val keys = map.keys
                for (i in 0 until (values.size)) {
                    coins.add(
                        Coin(
                            i,
                            null,
                            convertToShekels(values.elementAt(i)),
                            isHigherThanDollar(values.elementAt(i)),
                            keys.elementAt(i)
                        )
                    )
                }
                coins.forEach {
                    it.addImage()
                }
            }
            Logger.logInfo("rates list: $coins")
            _coinsLiveData.postValue(coins)

        }


    }

    private fun exceptionHandler() =
        CoroutineExceptionHandler { _, throwable ->
            when (throwable) {
                is CurrencyApiException.NoNetworkException -> _errorMessage.postValue(NETWORK_ERROR)
                is CurrencyApiException.HttpException -> _errorMessage.postValue(SERVER_ERROR)
                is CurrencyApiException.UnexpectedException -> _errorMessage.postValue(
                    UNEXPECTED_ERROR + throwable.localizedMessage
                )
            }
        }

    fun updateCoin(coin: Coin) {
        coinsLiveData.value?.forEach {
            if (coin.currency == it.currency) {
                coin.isClicked = !coin.isClicked
            }
        }
    }
}