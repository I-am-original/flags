package com.raccoon.flags.view

import android.content.Intent
import android.content.res.Configuration
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.raccoon.flags.R
import com.raccoon.flags.adapter.GridAdapter
import com.raccoon.flags.adapter.ItemClickListener
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.viewmodel.GridViewModel
import kotlinx.android.synthetic.main.activity_grid.*
import javax.inject.Inject

/**
 * Activity with configurable grid of all loaded countries
 */
class GridActivity : BaseActivity(), ItemClickListener {

    override val layoutResource = R.layout.activity_grid

    @Inject
    lateinit var viewModel: GridViewModel

    private val countriesAdapter: GridAdapter = GridAdapter(this)

    override fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
    }

    override fun setUpRecyclerView() {
        val columnCount =
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> 3
                    Configuration.ORIENTATION_PORTRAIT -> 2
                    else -> 2
                }

        recyclerViewGrid.layoutManager = GridLayoutManager(this, columnCount)
        recyclerViewGrid.itemAnimator = DefaultItemAnimator()
        recyclerViewGrid.adapter = countriesAdapter
    }

    override fun requestData() {
        viewModel.getDataList()
                .subscribe(
                        countriesAdapter::updateList,
                        this::showError
                )
    }

    /*
     * Item click with animated start of next activity
     */
    override fun onItemClicked(position: Int, data: DataModel, sharedElement: View) {

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.codeKey, data.alpha3Code)

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(sharedElement.findViewById(R.id.image_flag), DetailActivity.viewNameHeaderImage))

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
    }

}
