package cohen.dafna.middletermtest.models

data class Coin(
    var image: Int?,
    var valueInShekels: Double,
    var isHigherThanDollar: Boolean,
    var currency: String,
    var isClicked: Boolean = false
)

