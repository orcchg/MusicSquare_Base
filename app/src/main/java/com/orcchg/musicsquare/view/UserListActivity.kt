package com.orcchg.musicsquare.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.musicsquare.R
import com.orcchg.musicsquare.data.UserRepository
import com.orcchg.musicsquare.data.remote.Api

class UserListActivity : BaseActivity() {

    @BindView(R.id.rv_items) lateinit var items: RecyclerView

    private lateinit var adapter: UserListAdapter
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        ButterKnife.bind(this)

        repository = UserRepository(Api.provideCloud(), Api.provideDatabase(applicationContext))

        adapter = UserListAdapter { userId, _ -> openUserDetails(userId) }

        items.adapter = adapter
        items.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        repository.users().subscribe { adapter.items = it }
    }

    // --------------------------------------------------------------------------------------------
    private fun openUserDetails(userId: Int) {
        startActivity(UserDetailsActivity.getCallingIntent(this, userId))
    }
}
