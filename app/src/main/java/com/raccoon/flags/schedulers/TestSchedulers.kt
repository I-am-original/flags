package com.raccoon.flags.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TestSchedulers : ISchedulers {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()
}