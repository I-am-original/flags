package com.raccoon.flags.schedulers

import io.reactivex.Scheduler

interface ISchedulers {

    fun io(): Scheduler

    fun main(): Scheduler

}