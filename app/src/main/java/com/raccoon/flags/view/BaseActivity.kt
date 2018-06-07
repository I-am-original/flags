package com.raccoon.flags.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast.LENGTH_SHORT
import com.raccoon.flags.R
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        setUpToolbar(findViewById(R.id.toolbar))
        setUpRecyclerView()
        requestData()
    }

    abstract fun setUpToolbar(toolbar: Toolbar)

    abstract fun setUpRecyclerView()

    abstract fun requestData()

    protected fun showError(throwable: Throwable) {
        Snackbar.make(findViewById(android.R.id.content), throwable.localizedMessage, LENGTH_SHORT).show()
    }
}