package com.knilim.knilim.ui.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.knilim.knilim.R
import com.knilim.knilim.data.login.LoginRepository
import com.knilim.knilim.data.main.DialogManager
import com.knilim.knilim.data.main.DialogRepository
import com.knilim.knilim.data.main.MessageRepository
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.data.model.message.ContentType
import com.knilim.knilim.data.model.message.Message
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.*
import java.util.*

class ChatActivity : AppCompatActivity(), CoroutineScope by MainScope(),
    MessageInput.InputListener {

    private lateinit var dialog: Dialog

    private var adapter: MessagesListAdapter<Message>? = null

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        // 拿到传入的dialog对象
        dialog = intent.extras?.getString("dialog_id")?.let { chatViewModel.getDialogById(it) }!!

        initAdapter()

        // 加载历史消息
        chatViewModel.getMessagesByDialogId(dialog.id)
        chatViewModel.messages.observe(this, Observer {
            adapter?.addToEnd(it, true)
        })

        // 让消息发送按钮起作用
        input.setInputListener(this)
    }

    private fun initAdapter() {
        adapter = MessagesListAdapter<Message>(
            LoginRepository.user.id,
            ImageLoader { imageView, url, _ -> Glide.with(this).load(url).into(imageView) })
        messagesList.setAdapter(adapter)
    }

    private fun onNewMessage(message: Message) {
        // UI上插入
        adapter?.addToStart(message, true)
        // 插入到数据库中
        chatViewModel.insertMessage(message)
        // 更新最后一条消息的map
        MessageRepository.updateMessageMap(dialog.id, message)
        DialogManager.updateLastMessage(message)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        if (TextUtils.isEmpty(input.toString())) {
            return false
        }
        val message = Message(
            UUID.randomUUID().toString(),
            MessageRepository.judgeMessageType(dialog.id),
            ContentType.TEXT,
            LoginRepository.user.id,
            dialog.id,
            System.currentTimeMillis(),
            input.toString(),
            dialog.id
        )
        onNewMessage(message)
        return true
    }

    companion object {
        fun startChat(context: Context, dialogId: String) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("dialog_id", dialogId)
            context.startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}