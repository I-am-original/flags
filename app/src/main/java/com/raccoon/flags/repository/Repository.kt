package com.raccoon.flags.repository

import android.util.Log
import com.raccoon.flags.api.ApiService
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.schedulers.ISchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single responsibility class for requesting data via REST API and holding it
 */
@Singleton
class Repository
@Inject
constructor(private val apiService: ApiService, private val schedulers: ISchedulers) {

    // Serve as holder of data
    val subject: BehaviorSubject<List<DataModel>> = BehaviorSubject.create()

    init {
        getCountries()
    }

    private fun getCountries() {
        apiService.countriesList()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnError { Log.e(javaClass.simpleName, it.localizedMessage) }
                .retry()
                .subscribe({ subject.onNext(it) }, { subject.onError(it) })
    }

}