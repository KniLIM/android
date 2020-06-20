package com.knilim.knilim.ui.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.knilim.knilim.R
import com.knilim.knilim.data.model.user.Friend

class FriendAdapter(private val friends: List<Friend>, private val context: Context) :
    RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val friendImage: ImageView = view.findViewById(R.id.friend_image)
        val friendName: TextView = view.findViewById(R.id.friend_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: FriendAdapter.ViewHolder, position: Int) {
        val friend = friends[position]
        Glide.with(context).load(friend.avatar)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(6))).into(holder.friendImage)
        holder.friendName.text = friend.nickname
    }
}