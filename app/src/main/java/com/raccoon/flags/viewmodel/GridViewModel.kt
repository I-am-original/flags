package com.raccoon.flags.viewmodel

import android.arch.lifecycle.ViewModel
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.model.BusinessModel
import io.reactivex.Observable
import javax.inject.Inject

class GridViewModel
@Inject
constructor(private val model: BusinessModel) : ViewModel() {

    fun getDataList(): Observable<List<DataModel>> = model.dataList()

}
