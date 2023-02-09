package com.nihilus13.data.di.component

import android.app.Application
import android.content.Context
import com.nihilus13.data.di.DatabaseModule
import com.nihilus13.data.di.NetworkModule
import com.nihilus13.data.di.RepositoryModule
import com.nihilus13.data.di.UseCaseModule
import com.nihilus13.data.di.UtilsModule
import com.nihilus13.domain.usecase.LoadDetailsUseCase
import com.nihilus13.domain.usecase.SearchUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UtilsModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

    fun getSearchUseCase(): SearchUseCase

    fun getLoadDetailsUseCase(): LoadDetailsUseCase

    fun inject(application: Application)
}
