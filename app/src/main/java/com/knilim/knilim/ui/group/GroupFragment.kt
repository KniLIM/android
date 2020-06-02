package com.knilim.knilim.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.knilim.knilim.R

class GroupFragment : Fragment() {

    private lateinit var groupViewModel: GroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupViewModel =
            ViewModelProviders.of(this).get(GroupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_group, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        groupViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
