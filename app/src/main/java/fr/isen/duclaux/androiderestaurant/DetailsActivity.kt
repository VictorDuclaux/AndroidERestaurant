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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val _item = intent.getSerializableExtra("item") as Item


            binding.PagerDetails.adapter = DetailsViewAdapter(this, listOf("http://i.imgur.com/DvpvklR.png","https://resize-parismatch.lanmedia.fr/img/var/news/storage/images/paris-match/actu/environnement/un-loup-aurait-ete-photographie-dans-le-nord-de-la-france-une-premiere-depuis-un-siecle-1682466/27431692-1-fre-FR/Un-loup-aurait-ete-photographie-dans-le-nord-de-la-France-une-premiere-depuis-un-siecle.jpg\n"))

        val img = findViewById<ImageView>(R.id.ImagePourPicasso)
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(img);
    }
// https://resize-parismatch.lanmedia.fr/img/var/news/storage/images/paris-match/actu/environnement/un-loup-aurait-ete-photographie-dans-le-nord-de-la-france-une-premiere-depuis-un-siecle-1682466/27431692-1-fre-FR/Un-loup-aurait-ete-photographie-dans-le-nord-de-la-France-une-premiere-depuis-un-siecle.jpg


}

