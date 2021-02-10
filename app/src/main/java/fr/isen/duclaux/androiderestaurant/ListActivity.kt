package fr.isen.duclaux.androiderestaurant

import NameAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.duclaux.androiderestaurant.databinding.ActivityListBinding
import org.json.JSONObject

private lateinit var binding: ActivityListBinding

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

class ListActivity : BaseActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as ItemType

        loadData(selectedItem)

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("ListActivity","Destroyed.")
    }

    private fun loadData(selectedItem: ItemType){

        val loader = Loader()
        loader.show(this, "récupération du menu")

        val postUrl = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        postData.put( "id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, postUrl, postData, { response ->
                loader.hide(this)
                val dataResult = GsonBuilder().create().fromJson(response.toString(), DataResult::class.java)
                val items = dataResult.data.firstOrNull { it.name == ItemType.categoryTitle(selectedItem) }
                loadList(items?.items)
            },
            {
                loader.hide(this)
                Log.e("ListActivity", it.toString())
            })

        requestQueue.add(jsonObjectRequest)
    }

    private fun loadList(items: List<Item>?) {
        items?.let {
            val adapter = NameAdapter(it) { item ->
                val intent2 = Intent(this, DetailsActivity::class.java)
                intent2.putExtra(DetailsActivity.DETAILS, item)
                startActivity(intent2)
            }
            binding.Recycler.adapter = adapter
            binding.Recycler.layoutManager = LinearLayoutManager(this)
        }
    }
}