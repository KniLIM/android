package com.knilim.knilim.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.knilim.knilim.R
import com.knilim.knilim.data.main.DialogManager
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.ui.chat.ChatActivity
import com.knilim.knilim.ui.main.MainViewModel
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.fragment_dialog.*

class DialogFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var dialogsListAdapter: DialogsListAdapter<Dialog>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 加载历史上的dialog
        mainViewModel.getDialogs()
        mainViewModel.dialogs.observe(viewLifecycleOwner, Observer {
            dialogsListAdapter.setItems(it)
        })

        DialogManager.lastMessage.observe(viewLifecycleOwner, Observer {
            dialogsListAdapter.updateDialogWithMessage(it.dialogId, it)
        })

        initAdapter()
    }

    private fun initAdapter() {
        dialogsListAdapter = DialogsListAdapter(R.layout.item_dialog,
            ImageLoader { imageView, url, _ ->
                Glide.with(requireContext()).load(url).into(imageView!!)
            })
        dialogsListAdapter.setOnDialogClickListener {
            ChatActivity.startChat(requireContext(), it.id)
        }
        dialogs_list.setAdapter(dialogsListAdapter)
    }
}
