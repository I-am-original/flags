package com.raccoon.flags.view

import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.raccoon.flags.R
import com.raccoon.flags.adapter.DetailsAdapter
import com.raccoon.flags.api.DetailedModel
import com.raccoon.flags.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {
    override var layoutResource = R.layout.activity_details

    companion object {
        const val codeKey = "CODE_KEY"
        const val viewNameHeaderImage = "detail:header:image"
    }

    @Inject
    lateinit var viewModel: DetailViewModel

    private val detailsAdapter = DetailsAdapter()

    override fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        ViewCompat.setTransitionName(toolbarImage, viewNameHeaderImage)
    }

    override fun setUpRecyclerView() {
        infoRecyclerView.layoutManager = LinearLayoutManager(this)
        infoRecyclerView.adapter = detailsAdapter
    }

    override fun requestData() {
        viewModel.singleCountryData(intent.getStringExtra(codeKey))
                .subscribe(
                        { model: DetailedModel? ->
                            model?.let {
                                collapsingToolbar.title = it.header.first
                                Picasso.get().load(it.header.second).into(toolbarImage)
                                detailsAdapter.updateData(it.content)
                            }
                        }, this::showError
                )
    }
}
