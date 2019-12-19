package com.akshay.roommvvm.ui.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.akshay.roommvvm.R
import com.akshay.roommvvm.di.component.ActivityComponent
import com.akshay.roommvvm.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_create.*

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
class AddActivity : BaseActivity<AddViewModel>() {

    companion object {
        const val TAG = "AddActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_create

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        val intent = getIntent().extras
        intent?.let {
            if (it.containsKey("name"))
                viewModel.onPrevNameChange(it.getString("name") ?: "name")

            if (it.containsKey("id"))
                viewModel.updateUserId(it.getLong("id", 0L))

            btnDelete.visibility = View.VISIBLE
            btnSave.text = resources.getString(R.string.btn_update)
        } ?: kotlin.run {
            btnDelete.visibility = View.GONE
            btnSave.text = resources.getString(R.string.btn_save)
        }

        btnSave.setOnClickListener { viewModel.addDataToDatabase() }

        btnDelete.setOnClickListener { viewModel.deleteUser() }

        et_name.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onNameChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.nameField.observe(this, Observer {
            if (et_name.text.toString() != it)
                et_name.setText(it)
        })

        viewModel.launchMain.observe(this, Observer {
            it.getIfNotHandled()?.run {
                goBack()
            }
        })

        viewModel.prevNameField.observe(this, Observer {
            if (et_name.text.toString() != it)
                et_name.setText(it)
        })
    }

}