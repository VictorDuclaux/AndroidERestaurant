package fr.isen.duclaux.androiderestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.duclaux.androiderestaurant.databinding.ActivityBasketItemsFragmentBinding

private lateinit var binding: ActivityBasketItemsFragmentBinding
class BasketItemsFragment(private val basket: Panier, private val delegate: BasketCellInterface) : Fragment(), BasketCellInterface {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityBasketItemsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            reloadData(it)
        }
    }

    private fun reloadData(context: Context) {
        binding.basketItemRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.basketItemRecyclerView.adapter = PanierAdapter(basket, context, this)
    }

    override fun onDeleteItem(item: PanierItem) {
        context?.let {
            basket.items.remove(item)
            basket.save(it)
            reloadData(it)
        }
    }

    override fun onShowDetail(item: PanierItem) {
        delegate.onShowDetail(item)
    }
}