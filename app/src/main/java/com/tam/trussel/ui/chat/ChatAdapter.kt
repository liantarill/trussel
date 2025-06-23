package com.tam.trussel.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tam.trussel.R
import com.tam.trussel.databinding.ItemChatBinding

class ChatAdapter(private val chatList: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    // Data class untuk merepresentasikan item chat
    data class Chat(
        val senderName: String,
        val lastMessage: String,
        val time: String,
        val isOnline: Boolean,
        val messageType: String,
        val hasUnread: Boolean,
        val avatarRes: Int
    )

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemChatBinding.bind(itemView)

        fun bind(chat: Chat) {
            binding.textViewSenderName.text = chat.senderName
            binding.textViewLastMessage.text = chat.lastMessage
            binding.textViewTime.text = chat.time
            binding.textViewMessageType.text = chat.messageType

            binding.viewOnlineIndicator.backgroundTintList = if (chat.isOnline) {
                itemView.context.getColorStateList(R.color.green_500)
            } else {
                itemView.context.getColorStateList(R.color.gray_400)
            }

            binding.viewUnreadIndicator.visibility = if (chat.hasUnread) View.VISIBLE else View.GONE
            binding.imageViewAvatar.setImageResource(chat.avatarRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val currentChat = chatList[position]
        holder.bind(currentChat)

        holder.itemView.setOnClickListener {
            androidx.navigation.Navigation.findNavController(it)
                .navigate(R.id.action_navigation_chat_to_navigaton_chat_person)
        }
    }

    override fun getItemCount() = chatList.size
}