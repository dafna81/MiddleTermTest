package cohen.dafna.middletermtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cohen.dafna.middletermtest.R
import cohen.dafna.middletermtest.databinding.LargeCoinItemBinding
import cohen.dafna.middletermtest.databinding.SmallCoinItemBinding
import cohen.dafna.middletermtest.models.Coin
import cohen.dafna.middletermtest.ui.toPx
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class CurrencyAdapter(var rates: List<Coin>, var callback: (MaterialCardView, MaterialCardView) -> Unit) :
    RecyclerView.Adapter<CurrencyAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return if (!rates[viewType].isHigherThanDollar)
            SmallVH(
                SmallCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        else
            LargeVH(
                LargeCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val coin = rates.sortedBy { it.valueInShekels }.reversed()[position]
        if (coin.image != null) {
            holder.coinFrontImage.visibility = ShapeableImageView.VISIBLE
            holder.coinFrontText.visibility = TextView.GONE
//            holder.coinFrontImage.setImageResource(coin.image!!)
            Picasso.get().load(coin.image!!).into(holder.coinFrontImage)
        } else {
            holder.coinFrontText.text = coin.currency
        }
        holder.coinBackText.text =
            "${coin.currency} 1 = ILS ${String.format("%.3f", coin.valueInShekels)}"

        holder.itemView.setOnClickListener {
            coin.isClicked = !coin.isClicked
            if (coin.isClicked) {
                callback(holder.cardBack, holder.cardFront)
            } else {
                callback(holder.cardFront, holder.cardBack)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    open class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coinFrontText: TextView
        var coinBackText: TextView
        var coinFrontImage: ShapeableImageView
        var cardFront: MaterialCardView
        var cardBack: MaterialCardView

        init {
            coinFrontText = itemView.findViewById(R.id.text_view_front_coin)
            coinBackText = itemView.findViewById(R.id.text_view_back_coin)
            coinFrontImage =
                itemView.findViewById(R.id.round_image_view_front_coin)
            cardBack = itemView.findViewById(R.id.card_back)
            cardFront = itemView.findViewById(R.id.card_front)
        }

    }

    class SmallVH(binding: SmallCoinItemBinding) : VH(binding.root)
    class LargeVH(binding: LargeCoinItemBinding) : VH(binding.root)

}


