package com.raccoon.flags

import com.raccoon.flags.schedulers.ISchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Test scheduler - trampoline, will perform all work in one thread
 */
object TestSchedulers : ISchedulers {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()
}