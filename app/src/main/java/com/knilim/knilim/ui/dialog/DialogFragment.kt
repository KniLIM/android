package com.knilim.knilim.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.knilim.knilim.R
import com.knilim.knilim.data.model.dialog.Dialog
import com.knilim.knilim.ui.main.MainViewModel
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.fragment_dialog.*

class DialogFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dialog, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialogs_list.setOnClickListener {
            mainViewModel.initDatabase()
        }
        mainViewModel.messages.observe(viewLifecycleOwner, Observer {
            mainViewModel.updateMessagesMap(it)
        })

        mainViewModel.dialogs.observe(viewLifecycleOwner, Observer {
            val dialogsListAdapter = DialogsListAdapter<Dialog>(R.layout.item_dialog,
                ImageLoader { imageView, url, _ ->
                    Glide.with(requireContext()).load(url).into(imageView!!)
                })
            dialogsListAdapter.setItems(it)
            dialogs_list.setAdapter(dialogsListAdapter)
        })
    }
}
