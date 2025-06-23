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
import com.tam.trussel.UserList
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

//        binding.buttonChat.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_profile_to_navigation_chat)
//        }
        binding.profilePict.setOnClickListener {
            // Print semua user yang terdaftar ke Logcat
            printRegisteredUsers()
            Toast.makeText(requireContext(), "Daftar user telah dicetak di Logcat", Toast.LENGTH_SHORT).show()
        }
        binding.buttonMyOrder.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_my_order)
        }
    }

    private fun printRegisteredUsers() {
        val currentUser = sessionManager.getLoggedInUser()
        println("===== DAFTAR USER TERDAFTAR =====")
        UserList.users.forEach { user ->
            println("Email: ${user.email}, Username: ${user.username}")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}