package com.orcchg.musicsquare.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.musicsquare.R
import com.orcchg.musicsquare.data.UserRepository
import com.orcchg.musicsquare.data.remote.Api
import com.orcchg.musicsquare.domain.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class UserListActivity : BaseActivity() {

    @BindView(R.id.rv_items) lateinit var items: RecyclerView

    private val adapter = UserListAdapter()
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        ButterKnife.bind(this)

        repository = UserRepository(Api.provideCloud())

        items.adapter = adapter
        items.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        repository.users(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                adapter.items = response.body()!!
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Timber.e(t, "Failed to get users")
            }
        })
    }
}
