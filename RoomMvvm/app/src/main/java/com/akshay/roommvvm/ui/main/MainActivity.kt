package com.akshay.roommvvm.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.roommvvm.R
import com.akshay.roommvvm.di.component.ActivityComponent
import com.akshay.roommvvm.ui.add.AddActivity
import com.akshay.roommvvm.ui.base.BaseActivity
import com.akshay.roommvvm.ui.main.user.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        fabCreate.setOnClickListener {
            viewModel.launchAddDataActivity()
        }

        rvList.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.allUser.observe(this, Observer {
            userAdapter.appendData(it)
        })

        viewModel.launchAddData.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, AddActivity::class.java))
            }
        })
    }
}
