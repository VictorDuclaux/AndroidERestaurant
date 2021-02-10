package fr.isen.duclaux.androiderestaurant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.duclaux.androiderestaurant.databinding.ActivityRegisterBinding
import fr.isen.duclaux.androiderestaurant.databinding.ActivityRegisterBisBinding
import org.json.JSONObject
import org.w3c.dom.Text

private lateinit var binding: ActivityRegisterBisBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.BoutonEnregistrement.setOnClickListener {
            if (verifFormulaire()) {
                val msg = Toast.makeText(this, "Ok !", Toast.LENGTH_SHORT)
                msg.show()
                EnvoieDuGugus()
            } else {
                val msg = Toast.makeText(this, "Veuillez remplir le formulaire.", Toast.LENGTH_SHORT)
                msg.show()
            }
        }
    }

    //id_shop, firstname, lastname, address, email, password
    private fun EnvoieDuGugus() {
        val postUrl = "http://test.api.catering.bluecodegames.com/user/register"
        val requestQueue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        postData.put("id_shop", "1")
        postData.put("firstname", binding.FormulairePrenom.text)
        postData.put("lastname", binding.FormulaireNom.text)
        postData.put("address", binding.FormulaireAdresse.text)
        postData.put("email", binding.FormulaireEmail.text)
        postData.put("password", binding.FormulaireMDP.text)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, postUrl, postData, {
                val json = GsonBuilder().create().fromJson(it.toString(), JsonUser::class.java)
                Log.d("RegisterActivity", it.toString())
            },
            {
                Log.e("RegisterActivity", it.toString())
            })

        requestQueue.add(jsonObjectRequest)
    }

    //L'horreur.
    private fun verifFormulaire(): Boolean {
        if (!findViewById<EditText>(R.id.FormulaireNom).text.isNotEmpty()) {
            return false
        } else {
            if (!findViewById<EditText>(R.id.FormulairePrenom).text.isNotEmpty()) {
                return false
            } else {
                if (!findViewById<EditText>(R.id.FormulaireAdresse).text.isNotEmpty()) {
                    return false
                } else {
                    if (!findViewById<EditText>(R.id.FormulaireEmail).text.isNotEmpty()) {
                        return false
                    } else {
                        return findViewById<EditText>(R.id.FormulaireMDP).text.isNotEmpty()
                    }
                }
            }
        }
    }
}