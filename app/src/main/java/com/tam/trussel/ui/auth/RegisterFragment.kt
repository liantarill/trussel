package com.tam.trussel.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tam.trussel.R
import com.tam.trussel.User
import com.tam.trussel.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPasswordStrengthIndicator()
        setupButtonListeners()
    }

    private fun setupPasswordStrengthIndicator() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updatePasswordStrength(s.toString())
            }
        })
    }

    private fun updatePasswordStrength(password: String) {
        val strength = when {
            password.isEmpty() -> {
                binding.progressBarPasswordStrength.progress = 0
                "Kosong"
            }
            password.length < 6 -> {
                binding.progressBarPasswordStrength.progress = 25
                "Lemah"
            }
            password.length < 8 -> {
                binding.progressBarPasswordStrength.progress = 50
                "Sedang"
            }
            password.length < 10 -> {
                binding.progressBarPasswordStrength.progress = 75
                "Kuat"
            }
            else -> {
                binding.progressBarPasswordStrength.progress = 100
                "Sangat Kuat"
            }
        }
        binding.progressBarPasswordStrength.progressTintList = when {
            password.isEmpty() -> resources.getColorStateList(R.color.gray_300, null)
            password.length < 6 -> resources.getColorStateList(R.color.red_500, null)
            password.length < 8 -> resources.getColorStateList(R.color.orange_500, null)
            password.length < 10 -> resources.getColorStateList(R.color.yellow_500, null)
            else -> resources.getColorStateList(R.color.green_500, null)
        }
        binding.progressBarPasswordStrength.contentDescription = "Kekuatan password: $strength"
    }

    private fun setupButtonListeners() {
        binding.textViewLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.buttonSignup.setOnClickListener {
            val user = User(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
                username = binding.editUsername.text.toString(),
                phone = binding.editNoTelp.text.toString()
            )

            sessionManager.saveLoginSession(user)
            Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun registerUser() {
        val username = binding.editUsername.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val phone = binding.editNoTelp.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextPasswordConfirmation.text.toString().trim()
        val agreedToTerms = binding.checkBoxTerms.isChecked
        val subscribeToNewsletter = binding.checkBoxNewsletter.isChecked

        if (!validateInputs(username, email, phone, password, confirmPassword, agreedToTerms)) {
            return
        }

        // Create user instance
        val user = User(
            email = email,
            password = password,
            username = username,
            phone = phone
        )

        // Save user data to SharedPreferences
        sessionManager.saveLoginSession(user)

        // Show success message
        Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show()

        // Navigate to login or home screen
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun validateInputs(
        username: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String,
        agreedToTerms: Boolean
    ): Boolean {
        if (username.isEmpty()) {
            binding.editUsername.error = "Username harus diisi"
            return false
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Email harus diisi"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Email tidak valid"
            return false
        }

        if (phone.isEmpty()) {
            binding.editNoTelp.error = "Nomor telepon harus diisi"
            return false
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password harus diisi"
            return false
        }

        if (password.length < 6) {
            binding.editTextPassword.error = "Password minimal 6 karakter"
            return false
        }

        if (password != confirmPassword) {
            binding.editTextPasswordConfirmation.error = "Password tidak cocok"
            return false
        }

        if (!agreedToTerms) {
            Toast.makeText(requireContext(), "Anda harus menyetujui syarat dan ketentuan", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}