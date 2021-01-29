import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.duclaux.androiderestaurant.R

class NameAdapter(private val names: List<String>) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    // Create a Unit function as variable
    var itemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        // Inflating R.layout.name_item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        // Getting element from names list at this position
        val element = names[position]
        // Updating the text of the txtName with this element
        holder.txtName.text = element

        // Adding an OnClickLister to the holder.itemView
        holder.itemView.setOnClickListener {
            // Invoking itemClickListener and passing it the position and name
            itemClickListener?.invoke(position, element)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById(R.id.txtName) as TextView
    }
}