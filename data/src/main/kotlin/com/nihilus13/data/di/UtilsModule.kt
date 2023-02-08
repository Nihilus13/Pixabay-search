package com.nihilus13.data.di

import com.nihilus13.data.date.DateSource
import com.nihilus13.data.date.DateSourceImpl
import dagger.Module
import dagger.Provides
import java.util.Calendar

@Module
internal object UtilsModule {

    @Provides
    fun provideCalendar(): Calendar = Calendar.getInstance()

    @Provides
    fun provideDateSource(dateSource: DateSourceImpl): DateSource = dateSource
}
