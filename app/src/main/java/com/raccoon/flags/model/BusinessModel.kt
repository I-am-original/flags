package com.raccoon.flags.model

import com.raccoon.flags.api.DataModel
import com.raccoon.flags.repository.Repository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Business logic part
 */
@Singleton
class BusinessModel
@Inject
constructor(private val repository: Repository) {

    fun dataList(): Observable<List<DataModel>> = repository.subject

    fun singleItemData(countryCode: String): Single<DataModel> =
            dataList().flatMapIterable { it }
                    .filter { it.alpha3Code == countryCode }
                    .firstOrError()

}
