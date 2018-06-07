package com.raccoon.flags.di

import com.raccoon.flags.view.DetailActivity
import com.raccoon.flags.view.GridActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindGridActivity(): GridActivity

    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): DetailActivity

}