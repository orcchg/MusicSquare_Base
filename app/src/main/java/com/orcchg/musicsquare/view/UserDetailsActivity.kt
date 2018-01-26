package com.orcchg.musicsquare.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.orcchg.musicsquare.R
import com.orcchg.musicsquare.data.UserRepository
import com.orcchg.musicsquare.data.remote.Api
import com.orcchg.musicsquare.domain.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class UserDetailsActivity : BaseActivity() {

    @BindView(R.id.iv_image) lateinit var image: SimpleDraweeView
    @BindView(R.id.tv_title) lateinit var title: TextView

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"

        fun getCallingIntent(context: Context, userId: Int): Intent {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            return intent
        }
    }

    private lateinit var repository: UserRepository

    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        ButterKnife.bind(this)

        repository = UserRepository(Api.provideCloud(), Api.provideDatabase(applicationContext))
    }

    override fun onStart() {
        super.onStart()
        val userId = intent.getIntExtra(EXTRA_USER_ID, -1)

        repository.user(userId,
                object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val user = response.body()
                        if (user != null) {
                            image.setImageURI(Uri.parse(user.avatar_url))
                            title.text = user.login
                        } else {
                            Timber.e("Failed to get user by id: $userId")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Timber.e(t, "Network error")
                    }
                })
    }
}
