package com.knilim.knilim.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.knilim.base.Utils
import com.knilim.knilim.R
import com.knilim.knilim.data.main.DialogRepository
import com.knilim.knilim.data.main.IUserRepository
import com.knilim.knilim.data.main.MessageRepository
import com.knilim.knilim.data.main.UserRepository
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.ContentType
import com.knilim.knilim.data.model.message.Message
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity(), MessageInput.InputListener {

    private lateinit var messages: LiveData<List<Message>>

    private lateinit var dialog: Dialog

    private var adapter: MessagesListAdapter<Message>? = null

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 拿到传入的dialog对象
        dialog = intent.extras?.getString("dialog_id")?.let { DialogRepository.getDialogById(it) }!!

        // 初始化message列表
        messages = Utils.db.messageDao().getMessagesByDialog(dialog.id)

        // 监听message列表的变化
        messages.observe(this, Observer {
            // 废弃之前的adapter，让gc工作
            adapter = null

            adapter = MessagesListAdapter<Message>(
                UserRepository.user.id,
                ImageLoader { imageView, url, _ -> Glide.with(this).load(url).into(imageView) })
            adapter!!.addToEnd(it, true)
            messagesList.setAdapter(adapter)
        })

        input.setInputListener(this)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        val message = Message(
            UUID.randomUUID().toString(),
            MessageRepository.judgeMessageType(dialog.id),
            ContentType.TEXT,
            UserRepository.user.id,
            dialog.id,
            System.currentTimeMillis(),
            input.toString(),
            dialog.id
        )
chatViewModel.insertMessage(message)
        adapter?.addToStart(message, true)
        return true
    }
}