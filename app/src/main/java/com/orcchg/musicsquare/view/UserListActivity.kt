package com.orcchg.musicsquare.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.musicsquare.R

class UserListActivity : BaseActivity() {

    @BindView(R.id.rv_items) lateinit var items: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        ButterKnife.bind(this)

        items.layoutManager = LinearLayoutManager(this)
    }
}
