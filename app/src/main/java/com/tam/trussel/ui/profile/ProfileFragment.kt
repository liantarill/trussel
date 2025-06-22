package com.tam.trussel.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tam.trussel.R
import androidx.navigation.fragment.findNavController
import com.tam.trussel.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMyOrder.setOnClickListener {
            // Di sini bisa juga kamu tambahkan validasi email/password
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_my_order)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}