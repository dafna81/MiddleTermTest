package cohen.dafna.middletermtest.models

data class Coin(
    var id: Int,
    var image: Int?,
    var valueInShekels: Double,
    var isHigherThanDollar: Boolean,
    var currency: String,
    var isClicked: Boolean = false
)

