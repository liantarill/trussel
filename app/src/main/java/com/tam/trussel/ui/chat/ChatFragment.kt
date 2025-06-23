package com.tam.trussel.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tam.trussel.R
import com.tam.trussel.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Buat data dummy untuk contoh
        val chatList = listOf(
            ChatAdapter.Chat(
                senderName = "Ahmad Rizki",
                lastMessage = "Apakah iPhone masih tersedia?",
                time = "10:30",
                isOnline = true,
                messageType = "Pertanyaan",
                hasUnread = true,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Bintang ",
                lastMessage = "Saya tertarik dengan produknya",
                time = "09:15",
                isOnline = false,
                messageType = "Minat",
                hasUnread = false,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Lutpi",
                lastMessage = "Terima kasih sudah membeli produk saya",
                time = "Kemarin",
                isOnline = true,
                messageType = "Transaksi",
                hasUnread = true,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Alka",
                lastMessage = "Bisa nego harga sampai Rp 11.000.000?",
                time = "Kemarin",
                isOnline = false,
                messageType = "Penawaran",
                hasUnread = false,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Lian",
                lastMessage = "Produk sudah dikirim hari ini",
                time = "2 hari lalu",
                isOnline = true,
                messageType = "Pengiriman",
                hasUnread = true,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Sintia",
                lastMessage = "Nego min berapa ya?",
                time = "1 jam lalu",
                isOnline = true,
                messageType = "Penawaran",
                hasUnread = true,
                avatarRes = R.drawable.profile_nav
            ),
            ChatAdapter.Chat(
                senderName = "Rahma",
                lastMessage = "Halo?",
                time = "1 menit lalu",
                isOnline = true,
                messageType = "Pengiriman",
                hasUnread = true,
                avatarRes = R.drawable.profile_nav
            )
        )

        // Setup RecyclerView
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ChatAdapter(chatList)

            // Optional: Tambahkan padding untuk menghindari overlap dengan input message
            setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.chat_input_height))
            clipToPadding = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}