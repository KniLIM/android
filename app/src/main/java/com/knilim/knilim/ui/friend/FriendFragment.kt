package com.knilim.knilim.ui.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.knilim.knilim.R
import com.knilim.knilim.data.main.FriendRepository
import com.knilim.knilim.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_friend.*

class FriendFragment : Fragment() {

    private val friendViewModel : FriendViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 设置recyclerView
        val friendLayoutManager = LinearLayoutManager(requireContext())
        friendViewModel.friends.observe(viewLifecycleOwner, Observer {
            val friendAdapter = FriendAdapter(it, requireContext())
            friend_recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = friendLayoutManager
                adapter = friendAdapter
            }
        })
    }
}
