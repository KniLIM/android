package com.knilim.knilim.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.knilim.knilim.R
import com.knilim.knilim.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_dialog.*

class DialogFragment : Fragment() {

    private val dialogModel: MainViewModel by viewModels()

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
                dialogModel.initDatabase()
        }
        dialogModel.messages.observe(viewLifecycleOwner, Observer {
            dialogModel.updateMessagesMap(it)
        })
    }
}
