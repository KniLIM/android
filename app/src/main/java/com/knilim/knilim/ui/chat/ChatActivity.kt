package com.knilim.knilim.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.knilim.base.Utils
import com.knilim.knilim.R
import com.knilim.knilim.data.main.DialogRepository
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
import java.util.concurrent.CopyOnWriteArrayList

class ChatActivity : AppCompatActivity(), MessageInput.InputListener {

    private lateinit var messages: CopyOnWriteArrayList<Message>

    private lateinit var dialog: Dialog

    private var adapter: MessagesListAdapter<Message>? = null

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 拿到传入的dialog对象
        dialog = intent.extras?.getString("dialog_id")?.let { DialogRepository.getDialogById(it) }!!

        chatViewModel.setMessagesLiveData(dialog.id)

        // 初始化message列表
        messages = MessageRepository.getMessagesByDialogId(dialog.id)

        // 初始化adapter
        initAdapter()

        // 监听数据库消息列表的变化
        chatViewModel.messages.observe(this, Observer {
            // 当数据库内容有变化时，及时将新的消息反映到UI上
            for(i in messages.size until it.size) {
                messages.add(it[i])
                insertMessagesList(it[i])
            }
        })

        // 让消息发送按钮起作用
        input.setInputListener(this)
    }

    private fun initAdapter() {
        adapter = MessagesListAdapter<Message>(
            UserRepository.user.id,
            ImageLoader { imageView, url, _ -> Glide.with(this).load(url).into(imageView) })
        adapter!!.addToEnd(messages, true)
        messagesList.setAdapter(adapter)
    }

    private fun insertMessagesList(message: Message) {
        adapter?.addToStart(message, true)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        if(TextUtils.isEmpty(input.toString())) {
            return false
        }

        insertMessagesList(
            Message(
                UUID.randomUUID().toString(),
                MessageRepository.judgeMessageType(dialog.id),
                ContentType.TEXT,
                UserRepository.user.id,
                dialog.id,
                System.currentTimeMillis(),
                input.toString(),
                dialog.id
            )
        )
        return true
    }
}