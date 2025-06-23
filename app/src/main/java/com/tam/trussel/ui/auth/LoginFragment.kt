package com.tam.trussel.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.internal.Objects
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tam.trussel.R
import com.tam.trussel.User
import com.tam.trussel.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val database = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sessionManager.isLoggedIn()) {
            navigateToHome()
            return
        }

        binding.buttonLogin.setOnClickListener {
            validateUser()
        }

        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateUser() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        database.child("users")
            .orderByChild("email")
            .equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val passwordFromDB = userSnapshot.child("password").getValue(String::class.java)

                            if (passwordFromDB == password) {
                                val user = userSnapshot.getValue(User::class.java)
                                if (user != null) {
                                    sessionManager.saveLoginSession(user)
                                    navigateToHome()
                                    return
                                }
                            }
                        }
                    }
                    Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun navigateToHome() {
        Toast.makeText(requireContext(), "Login berhasil!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}