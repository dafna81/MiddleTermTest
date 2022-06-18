package cohen.dafna.middletermtest.ui

import android.content.res.Resources
import android.util.TypedValue
import cohen.dafna.middletermtest.R
import cohen.dafna.middletermtest.models.Coin

fun Coin.addImage() {
    when(currency) {
        "ARS" -> image = R.drawable.coin_argentina
        "AUD" -> image = R.drawable.coin_australia
        "BRL" -> image = R.drawable.coin_brazil
        "CAD" -> image = R.drawable.coin_canada
        "CNY" -> image = R.drawable.coin_china
        "GBP" -> image = R.drawable.coin_england
        "EUR" -> image = R.drawable.coin_europe
        "INR" -> image = R.drawable.coin_india
        "ILS" -> image = R.drawable.coin_israel
        "JPY" -> image = R.drawable.coin_japan
        "MXN" -> image = R.drawable.coin_mexico
        "RUB" -> image = R.drawable.coin_russia
        "USD" -> image = R.drawable.coin_usa
        else -> image = null
    }
}

val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)