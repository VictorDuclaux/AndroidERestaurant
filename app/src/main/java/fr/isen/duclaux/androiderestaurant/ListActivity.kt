package fr.isen.duclaux.androiderestaurant

import NameAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.duclaux.androiderestaurant.databinding.ActivityListBinding
import org.json.JSONObject

private lateinit var binding: ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val rclcategory = findViewById<RecyclerView>(R.id.Recycler)

        // If size of the all items are equal and won't change for a better performance it's better to set setHasFixedSize to true
        rclcategory.setHasFixedSize(true)

        val categList =  loadData()
        Log.d("ListActivity", categList.toString())
        val namesAdapter = NameAdapter(categList)
        rclcategory.adapter = namesAdapter
        rclcategory.layoutManager = LinearLayoutManager(this)

        // Initializing namesAdapter.itemClickListener
        namesAdapter.itemClickListener = { position, name ->
            Toast.makeText(this, "position: $position - name: $name", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("ListActivity","Destroyed.")
    }

    private fun loadData(): List<String>{
        val postUrl = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        postData.put( "id_shop", "1")

        var categList: List<String> = listOf("Erreur","Erreur")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, postUrl, postData, {
                Log.d("ListActivity", categList.toString())
                val gson: DataResult = Gson().fromJson(it.toString(), DataResult::class.java)
                val categories: List<String> = gson.data.map {it.name}
                categList = categories
                Log.d("DetailsActivity", categList.toString())
            },
            {
                Log.e("ListActivity", it.toString())
            })

        requestQueue.add(jsonObjectRequest)

        return categList
    }




    /*//Inutile
    private fun getListOfNames(): MutableList<String> {
        val nameList = mutableListOf<String>()
        nameList.add("Ali")
        nameList.add("Sophia")
        nameList.add("Isabella")
        nameList.add("Mason")
        nameList.add("Jacob")
        nameList.add("William")
        nameList.add("Olivia")
        nameList.add("Jayden")
        nameList.add("Chloe")
        nameList.add("Ella")
        nameList.add("Anthony")
        nameList.add("Joshua")
        nameList.add("James")
        nameList.add("Grace")
        nameList.add("Samantha")
        nameList.add("Nicholas")
        nameList.add("Brianna")
        nameList.add("Justin")
        nameList.add("Lauren")
        nameList.add("Kimberly")

        return nameList
    }*/
}