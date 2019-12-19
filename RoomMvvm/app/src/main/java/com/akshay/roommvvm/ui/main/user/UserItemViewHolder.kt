package com.akshay.roommvvm.ui.main.user

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.akshay.roommvvm.R
import com.akshay.roommvvm.data.local.db.entity.User
import com.akshay.roommvvm.di.component.ViewHolderComponent
import com.akshay.roommvvm.ui.add.AddActivity
import com.akshay.roommvvm.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
class UserItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<User, UserItemViewModel>(R.layout.item_list, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        itemView.setOnClickListener { viewModel.launchAddDataActivity() }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tvName.text = it
        })

        viewModel.dateOfBirth.observe(this, Observer {
            itemView.tvDateOfBirth.text = itemView.resources.getString(R.string.date_of_birth, it)
        })

        viewModel.userId.observe(this, Observer {

        })

        viewModel.launchAddData.observe(this, Observer {
            it.getIfNotHandled()?.run {
                val intent = Intent(itemView.context, AddActivity::class.java)
                intent.putExtra("name", viewModel.name.value)
                intent.putExtra("id", viewModel.userId.value)
                itemView.context.startActivity(intent)
            }
        })
    }
}