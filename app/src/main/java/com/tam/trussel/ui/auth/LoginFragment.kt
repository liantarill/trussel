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
import com.tam.trussel.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val userList = listOf(
        User("lian@gmail.com", "lian123"),
        User("alka@gmail.com", "alka123"),
        User("lutpi@gmail.com", "lutpi123"),
        User("ilham@gmail.com", "ilham123"),
        User("bintang@gmail.com", "bintang123"),
        User("user1@gmail.com", "password123"),
        User("user2@gmail.com", "password456")
    )

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
            findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
            return  // Penting: return setelah navigate untuk menghindari eksekusi kode berikutnya
        }

        // Tampilkan daftar user di LogCat
        userList.forEach { user ->
            println("User: ${user.email} - ${user.password}")
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
        val user = User(email, password)

        if (user.isValid()) {
            if (userList.any { it.email == email && it.password == password }) {
                sessionManager.saveLoginSession(user)
                Toast.makeText(requireContext(), "Login sukses!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
            } else {
                Toast.makeText(requireContext(), "User/password salah", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Format email/password salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}