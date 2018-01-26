package com.orcchg.musicsquare.view

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.orcchg.musicsquare.R
import com.orcchg.musicsquare.domain.User

class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.iv_image) lateinit var image: SimpleDraweeView
    @BindView(R.id.tv_title) lateinit var title: TextView
    @BindView(R.id.tv_description) lateinit var description: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(model: User) {
        image.setImageURI(Uri.parse(model.avatar_url))
        title.text = model.login
        description.text = model.name
    }
}
