package com.knilim.knilim.ui.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.knilim.knilim.R

class FriendFragment : Fragment() {

    private lateinit var friendViewModel: FriendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        friendViewModel =
            ViewModelProviders.of(this).get(FriendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friend, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        friendViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}