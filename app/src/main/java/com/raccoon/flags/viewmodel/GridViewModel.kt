package com.raccoon.flags.viewmodel

import android.arch.lifecycle.ViewModel
import com.raccoon.flags.model.BusinessModel
import javax.inject.Inject

class GridViewModel
@Inject
constructor(private val model: BusinessModel) : ViewModel() {

    fun getDataList() = model.dataList()

}
