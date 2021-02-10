import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.duclaux.androiderestaurant.Item
import fr.isen.duclaux.androiderestaurant.R

import fr.isen.duclaux.androiderestaurant.databinding.RecyclerviewItemRowBinding

lateinit var binding: RecyclerviewItemRowBinding

class NameAdapter(
    private val entries: List<Item>,
    private val entryClickListener: (Item) -> Unit
) : RecyclerView.Adapter<NameAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RecyclerviewItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = entries[position]
        holder.layout.setOnClickListener {
            entryClickListener.invoke(item)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return entries.count()
    }

    class ItemViewHolder(ItemsBinding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(ItemsBinding.root) {
        val titleView: TextView = ItemsBinding.itemTitle
        val priceView: TextView = ItemsBinding.itemPrice
        val imageView: ImageView = ItemsBinding.itemImageView
        val layout = ItemsBinding.root
        fun bind(item: Item) {
            titleView.text = item.nameItem
            priceView.text =
                item.prices.first().price.toString() + " €" // dish.prices.first().price + " €"
            Picasso.get()
                .load(item.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}
