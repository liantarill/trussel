package com.tam.trussel.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tam.trussel.R
import androidx.navigation.fragment.findNavController
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

        binding.buttonLogout.setOnClickListener {
            sessionManager.logout()
            requireActivity().finishAffinity()

            // 2. Navigasi ke halaman login
//            findNavController().navigate(R.id.loginFragment)
//
//            // 3. Beri feedback ke user
//            Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}