package com.raccoon.flags.viewmodel

import android.arch.lifecycle.ViewModel
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.api.DetailedModel
import com.raccoon.flags.model.BusinessModel
import io.reactivex.Single
import javax.inject.Inject

class DetailViewModel
@Inject
constructor(private val model: BusinessModel) : ViewModel() {

    fun singleCountryData(countryCode: String): Single<DetailedModel> =
            model.singleItemData(countryCode)
                    .map { convertToPresentableData(it) }

    private fun convertToPresentableData(countryData: DataModel): DetailedModel =
            DetailedModel(countryData.name to countryData.getFlagUri(), countryData.toMapOfData())

}
