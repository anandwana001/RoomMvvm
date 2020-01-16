package com.akshay.roommvvm.ui.add

import android.content.Intent
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
        const val EXTRA_REPLY = "extra_reply"
        const val EXTRA_ID = "extra_id"
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
            btnSave.visibility = View.GONE
        } ?: kotlin.run {
            btnDelete.visibility = View.GONE
            btnSave.text = resources.getString(R.string.btn_save)
        }

        btnSave.setOnClickListener { viewModel.replyToAddDatabase() }

        btnDelete.setOnClickListener { viewModel.replyToDeleteDatabase() }

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

        viewModel.prevNameField.observe(this, Observer {
            if (et_name.text.toString() != it)
                et_name.setText(it)
        })

        viewModel.replyBackAdded.observe(this, Observer {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_REPLY, et_name.text.toString())
            setResult(RESULT_OK, replyIntent)
            finish()
        })

        viewModel.replyBackDeleted.observe(this, Observer {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_ID, viewModel.userIdField.value)
            setResult(RESULT_OK, replyIntent)
            finish()
        })
    }

}