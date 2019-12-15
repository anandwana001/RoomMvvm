package com.akshay.roommvvm.ui.main.user

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.akshay.roommvvm.data.local.db.entity.User
import com.akshay.roommvvm.ui.base.BaseAdapter

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
class UserAdapter(
    parentLifecycle: Lifecycle,
    user: ArrayList<User>
) : BaseAdapter<User, UserItemViewHolder>(parentLifecycle, user) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(parent)

}