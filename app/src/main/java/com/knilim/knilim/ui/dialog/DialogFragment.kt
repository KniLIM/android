package com.knilim.knilim.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.knilim.knilim.R
import com.knilim.knilim.ui.MainViewModel
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage

class DialogFragment : Fragment() {

    private lateinit var dialogModel: MainViewModel

    private lateinit var dialogs : List<IDialog<IMessage>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dialog, container, false)
        return root
    }
}
