package fr.isen.duclaux.androiderestaurant

import NameAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.duclaux.androiderestaurant.ItemType.Companion.categoryTitle
import fr.isen.duclaux.androiderestaurant.databinding.ActivityListBinding
import fr.isen.duclaux.androiderestaurant.databinding.RecyclerviewItemRowBinding
import org.json.JSONObject

private lateinit var binding: ActivityListBinding
private lateinit var binding2: RecyclerviewItemRowBinding

enum class ItemType {
    ENTREE, PLAT, DESSERT;
    companion object {
        fun categoryTitle(item: ItemType?) : String {
            return when(item) {
                ENTREE -> "Entrées"
                PLAT -> "Plats"
                DESSERT -> "Desserts"
                else -> ""
            }
        }
    }
}

class ListActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        binding2 = RecyclerviewItemRowBinding.inflate(layoutInflater)
        val view = binding.root
        val view2 = binding2.root
        setContentView(view)
        //setContentView(view2)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as ItemType

        loadData(selectedItem)

        //Pas utile.
        binding2.Carte.setOnClickListener {
            val intent2 = Intent(this, DetailsActivity::class.java)
            startActivity(intent2)

        }

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("ListActivity","Destroyed.")
    }

    private fun loadData(selectedItem: ItemType){
        val postUrl = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        postData.put( "id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, postUrl, postData, { response ->
                val dataResult = GsonBuilder().create().fromJson(response.toString(), DataResult::class.java)
                val items = dataResult.data.firstOrNull { it.name == ItemType.categoryTitle(selectedItem) }
                loadList(items?.items)
            },
            {
                Log.e("ListActivity", it.toString())
            })

        requestQueue.add(jsonObjectRequest)
    }

    private fun loadList(items: List<Item>?) {
        items?.let {
            val adapter = NameAdapter(it) { items ->
                Log.d("dish", "selected dish ${items.nameItem}")
                val intent2 = Intent(this, DetailsActivity::class.java)
                startActivity(intent2)
            }
            binding.Recycler.adapter = adapter
            binding.Recycler.layoutManager = LinearLayoutManager(this)
        }
    }
}