package fr.isen.duclaux.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.duclaux.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.BoutonEntrees.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra(CATEGORY_NAME, ItemType.ENTREE)
            startActivity(intent)

        }
        binding.BoutonPlats.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra(CATEGORY_NAME, ItemType.PLAT)
            startActivity(intent)

        }
        binding.BoutonDesserts.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra(CATEGORY_NAME, ItemType.DESSERT)
            startActivity(intent)

        }
        binding.BoutonRetard.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity","Destroyed.")
    }
    companion object{
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }

/*
    private fun displayCategories(categories: List<String>){
        binding.categoriesLoader.visibility = View.VISIBLE

        binding.listCategory.layoutManager = LinearLayoutManager(this)
        binding.listCategory.adapter = CategoryListAdapter(categories) {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("category", it)
            startActivity(intent)
        }
    }*/
}