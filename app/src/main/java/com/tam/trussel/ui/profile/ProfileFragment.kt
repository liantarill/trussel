package com.tam.trussel.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tam.trussel.R
import com.tam.trussel.User
import com.tam.trussel.databinding.FragmentProfileBinding
import com.tam.trussel.ui.auth.SessionManager

class ProfileFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tampilkan data user yang sedang login
//        val currentUser = sessionManager.getLoggedInUser()
//        currentUser?.let {
//            binding.textViewUsername.text = it.username
//            binding.textViewEmail.text = it.email
//            binding.textViewPhone.text = it.phone
//        }

        binding.buttonLogout.setOnClickListener {
            sessionManager.logout()
            requireActivity().finishAffinity()
        }

        binding.profilePict.setOnClickListener {
            // Print semua user yang terdaftar ke Logcat
            printRegisteredUsers()
            Toast.makeText(requireContext(), "Daftar user telah dicetak di Logcat", Toast.LENGTH_SHORT).show()
        }
    }

    private fun printRegisteredUsers() {
        // Untuk demo, kita akan print user yang sedang login dan beberapa contoh
        // Dalam implementasi nyata, Anda perlu menyimpan daftar user di database

        val currentUser = sessionManager.getLoggedInUser()
        println("===== DAFTAR USER TERDAFTAR =====")

        // User yang sedang login
        currentUser?.let {
            println("1. ${it.username} (${it.email}) - Telp: ${it.phone}")
        } ?: run {
            println("Tidak ada user yang login")
        }

        // Contoh user lain (dalam implementasi nyata, ambil dari database)
        println("2. lian (lian@gmail.com) - Telp: 08123456789")
        println("3. alka (alka@gmail.com) - Telp: 08234567890")
        println("4. lutpi (lutpi@gmail.com) - Telp: 08345678901")

        println("================================")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}