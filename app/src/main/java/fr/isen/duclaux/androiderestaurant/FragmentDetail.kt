package fr.isen.duclaux.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.duclaux.androiderestaurant.databinding.FragmentDishPhotoBinding

private lateinit var binding: FragmentDishPhotoBinding

class FragmentDetail: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDishPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.getString("Url")?.let {
            Picasso.get().load(it).into(binding.Photo)
        }
    }

    companion object {
        fun newInstance(picture: String): FragmentDetail {
            return FragmentDetail().apply{ arguments = Bundle().apply { putString("Url", picture) } }
        }
    }
}
