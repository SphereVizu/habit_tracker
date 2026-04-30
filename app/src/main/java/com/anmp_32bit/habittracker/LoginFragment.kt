package com.anmp_32bit.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.anmp_32bit.habittracker.databinding.FragmentLoginBinding
import kotlin.toString

class LoginFragment : Fragment() {
    private  lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val user = binding.inputUsername.text.toString()
            val pass = binding.inputPassword.text.toString()

            if(user == "32bit" && pass == "123"){
                val action = LoginFragmentDirections.actionDashboardFragment()
                it.findNavController().navigate(action)
            } else {
                binding.txtLoginError.visibility = View.VISIBLE
            }
        }
    }
}