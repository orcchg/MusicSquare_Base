package com.orcchg.musicsquare.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orcchg.musicsquare.R
import com.orcchg.musicsquare.domain.User

class UserListAdapter(val l: ((userId: Int, position: Int) -> Unit)?) : RecyclerView.Adapter<UserListViewHolder>() {

    var items: List<User> = emptyList()
        set(value) { field = value; notifyDataSetChanged() }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false)
        return UserListViewHolder(view, l)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
