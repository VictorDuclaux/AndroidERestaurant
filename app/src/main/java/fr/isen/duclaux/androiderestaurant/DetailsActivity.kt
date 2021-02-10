package fr.isen.duclaux.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import fr.isen.duclaux.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.duclaux.androiderestaurant.databinding.FragmentDishPhotoBinding
import org.json.JSONObject

private lateinit var binding: ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val DETAILS = "DETAILS"
    }
    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getSerializableExtra(DETAILS) as Item
        item?.let {
            binding.ItemTitre.text = item.nameItem
            binding.ItemIngredient.text = item.ingredients.map { it.nameIngrediant }?.joinToString(", ")
            binding.PagerDetails.adapter = DetailsViewAdapter(this, item.images)
            val price = itemCount * item.prices.first().price.toInt()
            binding.Compteur.text = itemCount.toString()
            binding.BoutonPanier.text = "Total : $price€"
        }



    }


}

