package fr.isen.duclaux.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.snackbar.Snackbar

import fr.isen.duclaux.androiderestaurant.databinding.ActivityDetailsBinding

import kotlin.math.max

private lateinit var binding: ActivityDetailsBinding

class DetailsActivity : BaseActivity() {

    companion object {
        const val DETAILS = "DETAILS"
    }

    lateinit var binding: ActivityDetailsBinding
    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(DETAILS) as? Item
        dish?.let {
            setupView(it)
        }
        val fragment = DetailViewFragment(dish)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    private fun setupView(dish: Item) {
        refreshShop(dish)

        binding.ImgMoins.setOnClickListener {
            itemCount = max(1, itemCount - 1)
            refreshShop(dish)
        }

        binding.ImgPlus.setOnClickListener {
            itemCount += 1
            refreshShop(dish)
        }

        binding.shopButton.setOnClickListener {
            addToBasket(dish, itemCount)
        }
    }

    private fun refreshShop(dish: Item) {
        val price = itemCount * dish.prices.first().price.toFloat()
        binding.itemCount.text = itemCount.toString()
        binding.shopButton.text = "Total :  $price€"
    }

    private fun addToBasket(dish: Item, count: Int) {
        val basket = Panier.getBasket(this)
        basket.addItem(PanierItem(dish, count))
        basket.save(this)
        refreshMenu(basket)
        Snackbar.make(binding.root, getString(R.string.TextePanierValidation), Snackbar.LENGTH_LONG).show()
    }

    private fun refreshMenu(basket: Panier) {
        invalidateOptionsMenu() // refresh l'affichage du menu
    }
}

