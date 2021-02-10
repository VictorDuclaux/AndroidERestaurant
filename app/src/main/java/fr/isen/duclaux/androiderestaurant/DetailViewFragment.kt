package fr.isen.duclaux.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.isen.duclaux.androiderestaurant.databinding.ActivityDetailViewFragmentBinding

private lateinit var binding: ActivityDetailViewFragmentBinding

class DetailViewFragment(private val dish: Item?) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityDetailViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingredientTextView.text = dish?.ingredients?.map { it.nameIngrediant }?.joinToString(", ")
        binding.dishTitleTextView.text = dish?.nameItem
        val appCompactActivity = activity as? AppCompatActivity
        appCompactActivity?.let {
            binding.viewPager.adapter = DetailsViewAdapter(it, dish?.images ?: listOf())
        }
    }
}