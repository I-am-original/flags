package com.raccoon.flags.model

import com.raccoon.flags.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessModel
@Inject
constructor(private val repository: Repository) {

    fun dataList() = repository.getCountriesList()

    fun singleItemData(countryCode: String) =
            dataList().flatMapIterable { it }
                    .filter { it.alpha3Code == countryCode }
                    .firstOrError()

}
