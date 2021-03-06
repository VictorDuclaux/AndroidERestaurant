package fr.isen.duclaux.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailsViewAdapter(activity: AppCompatActivity, val items: List<String>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return FragmentDetail.newInstance(items[position])
    }

}
