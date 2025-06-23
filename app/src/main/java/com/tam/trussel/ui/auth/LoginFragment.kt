package com.tam.trussel.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tam.trussel.R
import com.tam.trussel.User
import com.tam.trussel.UserList
import com.tam.trussel.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        // Langsung navigasi jika sudah login
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

        val registeredUser = sessionManager.getLoggedInUser()
        if (registeredUser != null && registeredUser.email == email && registeredUser.password == password) {
            sessionManager.saveLoginSession(registeredUser)
            navigateToHome()
            return
        }

        if (UserList.findUser(email, password) != null) {
            sessionManager.saveLoginSession(User(email, password))
            navigateToHome()
            return
        }

        Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
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