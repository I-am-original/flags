package com.raccoon.flags.repository

import com.raccoon.flags.api.ApiService
import com.raccoon.flags.api.DataModel
import com.raccoon.flags.schedulers.ISchedulers
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject
constructor(private val apiService: ApiService, private val schedulers: ISchedulers) {

    private val subject: BehaviorSubject<List<DataModel>> = BehaviorSubject.create()

    init {
        getCountries()
    }

    fun getCountriesList(): Observable<List<DataModel>> = subject

    private fun getCountries() {
        apiService.countriesList()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe { subject.onNext(it) }
    }

}