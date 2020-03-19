package com.akshay.roommvvm.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
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
        const val REQUEST_CODE = 101
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var userAdapter: UserAdapter

    val launcher = prepareCall(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let { extras ->
                val reply = extras.getStringExtra(AddActivity.EXTRA_REPLY)
                reply?.let { name ->
                    viewModel.addDataToDatabase(name)
                }
            }
        }
    }

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
                launcher.launch(Intent(this@MainActivity, AddActivity::class.java))
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1001 -> {
                if (resultCode == RESULT_OK) {
                    data?.let {

                        val userId = it.getLongExtra(AddActivity.EXTRA_ID, 0L)
                        viewModel.deleteUser(userId)
                    }
                }
            }
        }
    }
}
