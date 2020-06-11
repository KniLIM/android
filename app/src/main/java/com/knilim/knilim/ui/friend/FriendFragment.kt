package com.knilim.knilim.ui.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.knilim.knilim.R
import com.knilim.knilim.ui.main.MainViewModel

class FriendFragment : Fragment() {

    private lateinit var friendViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        friendViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friend, container, false)
        return root
    }
}
