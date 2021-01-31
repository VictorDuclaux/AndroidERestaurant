package fr.isen.duclaux.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.duclaux.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.duclaux.androiderestaurant.databinding.FragmentDishPhotoBinding
import org.json.JSONObject

private lateinit var binding: ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val dish = intent.getSerializableExtra("dish") as Dish

        dish.getAllPictures()?.let {
            binding.PagerDetails.adapter = DetailsViewAdapter(this, it)
        }*/

    }



}

