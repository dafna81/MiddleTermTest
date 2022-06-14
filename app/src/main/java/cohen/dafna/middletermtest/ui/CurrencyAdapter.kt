package cohen.dafna.middletermtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cohen.dafna.middletermtest.databinding.LargeCoinItemBinding
import cohen.dafna.middletermtest.databinding.SmallCoinItemBinding
import cohen.dafna.middletermtest.models.Coin
import cohen.dafna.middletermtest.ui.CurrencyAdapter.Const.LARGE_COIN
import cohen.dafna.middletermtest.ui.CurrencyAdapter.Const.SMALL_COIN

class CurrencyAdapter(private var rates: List<Coin>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SmallVH(var binding: SmallCoinItemBinding) : RecyclerView.ViewHolder(binding.root)
    class LargeVH(var binding: LargeCoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LARGE_COIN) {
            val largeBinding =
                LargeCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LargeVH(largeBinding)
        } else {
            val smallBinding =
                SmallCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SmallVH(smallBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val coin = rates[position]
        when (holder) {
            is SmallVH -> {
                holder.binding.textViewFrontCoin.text = coin.currency
                if (coin.image == null) {
                    holder.binding.textViewBackCoin.text =
                        "${coin.currency} 1 = ILS ${String.format("%.3f", coin.valueInShekels)}"
                } else {
                    holder.binding.roundImageViewFrontCoin.setImageResource(coin.image!!)
                }
                holder.binding.root.setOnClickListener {
                    coin.isClicked = !coin.isClicked
                    when {
                        coin.isClicked -> {
                            flip(coin, holder)
                        }
                        else -> {
                            flipBack(coin, holder)
                        }
                    }
                }
            }
            is LargeVH -> {
                holder.binding.textViewFrontCoin.text = coin.currency
                if (coin.image == null) {
                    holder.binding.textViewBackCoin.text =
                        "${coin.currency} 1 = ILS ${String.format("%.3f", coin.valueInShekels)}"
                } else {
                    holder.binding.roundImageViewFrontCoin.setImageResource(coin.image!!)
                }
                holder.binding.root.setOnClickListener {
                    coin.isClicked = !coin.isClicked
                    when {
                        coin.isClicked -> {
                            flip(coin, holder)
                        }
                        else -> {
                            flipBack(coin, holder)
                        }
                    }
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (rates[position].isHigherThanDollar) LARGE_COIN else SMALL_COIN
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    private fun flipBack(
        coin: Coin,
        holder: SmallVH
    ) {
        if (coin.image != null)
            flipCard(
                holder.itemView.context,
                holder.binding.roundImageViewFrontCoin,
                holder.binding.cardBack
            )
        else flipCard(
            holder.itemView.context,
            holder.binding.cardFront,
            holder.binding.cardBack
        )
    }

    private fun flip(
        coin: Coin,
        holder: SmallVH
    ) {
        if (coin.image != null)
            flipCard(
                holder.itemView.context,
                holder.binding.cardBack,
                holder.binding.roundImageViewFrontCoin
            )
        else flipCard(
            holder.itemView.context,
            holder.binding.cardBack,
            holder.binding.cardFront
        )
    }

    private fun flipBack(
        coin: Coin,
        holder: LargeVH
    ) {
        if (coin.image != null)
            flipCard(
                holder.itemView.context,
                holder.binding.roundImageViewFrontCoin,
                holder.binding.cardBack
            )
        else flipCard(
            holder.itemView.context,
            holder.binding.cardFront,
            holder.binding.cardBack
        )
    }

    private fun flip(
        coin: Coin,
        holder: LargeVH
    ) {
        if (coin.image != null)
            flipCard(
                holder.itemView.context,
                holder.binding.cardBack,
                holder.binding.roundImageViewFrontCoin
            )
        else flipCard(
            holder.itemView.context,
            holder.binding.cardBack,
            holder.binding.cardFront
        )
    }

    object Const {
        const val LARGE_COIN = 1
        const val SMALL_COIN = 0
    }
}


