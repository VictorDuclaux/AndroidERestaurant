package fr.isen.duclaux.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.duclaux.androiderestaurant.databinding.BasketCellBinding

interface BasketCellInterface {
    fun onDeleteItem(item: PanierItem)
    fun onShowDetail(item: PanierItem) // Optional
}

class PanierAdapter(
    private val panier: Panier,
    private val context: Context,
    private val delegate: BasketCellInterface
) : RecyclerView.Adapter<PanierAdapter.BasketViewHolder>() {

    class BasketViewHolder(binding: BasketCellBinding) : RecyclerView.ViewHolder(binding.root) {
        private val itemTitle: TextView = binding.basketItemTitle
        private val itemPrice: TextView = binding.basketItemPrice
        private val itemQuantity: TextView = binding.basketItemQuantity
        private val itemImageView: ImageView = binding.basketItemImageView
        private val deleteButton: ImageView = binding.basketItemDelete
        val layout = binding.root

        fun bind(
            item: PanierItem,
            context: Context,
            delegate: BasketCellInterface
        ) {
            itemTitle.text = item.item.nameItem //item.item.item.... :/
            itemPrice.text = "${item.item.prices.first().price}€"
            itemQuantity.text = "${context.getString(R.string.TexteQuantite)} ${item.nb.toString()}"
            Picasso.get()
                .load(item.item.getThumbnailUrl())
                .into(itemImageView)
            deleteButton.setOnClickListener {
                delegate.onDeleteItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            BasketCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item = panier.items[position]
        holder.bind(item, context, delegate)
    }

    override fun getItemCount(): Int {
        return panier.items.count()
    }
}
