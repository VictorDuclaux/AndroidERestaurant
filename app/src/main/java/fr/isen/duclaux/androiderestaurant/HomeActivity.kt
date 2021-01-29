package fr.isen.duclaux.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.duclaux.androiderestaurant.databinding.ActivityHomeBinding


private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.BoutonEntrees.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "1 2 3.", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)

        }
        binding.BoutonPlats.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "4 5 6.", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)

        }
        binding.BoutonDesserts.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "7 8 9.", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity","Destroyed.")
    }
}