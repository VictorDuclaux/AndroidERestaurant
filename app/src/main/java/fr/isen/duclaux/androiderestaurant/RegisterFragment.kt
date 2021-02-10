package fr.isen.duclaux.androiderestaurant

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.isen.duclaux.androiderestaurant.databinding.ActivityRegisterBinding
import fr.isen.duclaux.androiderestaurant.databinding.FragmentLoginBinding

private lateinit var binding: ActivityRegisterBinding

class RegisterFragment : Fragment() {


    var delegate: UserActivityFragmentInteraction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener {
            delegate?.makeRequest(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.lastname.text.toString(),
                binding.firstname.text.toString(),
                false
            )
        }

        binding.buttonRegister.setOnClickListener {
            delegate?.showLogin()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is UserActivityFragmentInteraction) {
            delegate = context
        }
    }
}