package fr.isen.duclaux.androiderestaurant

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtName = itemView.findViewById(R.id.txtName) as TextView
}