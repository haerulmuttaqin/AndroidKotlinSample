package co.id.cpn.navsample

import android.app.Application
import co.id.cpn.data.di.databaseModule
import co.id.cpn.data.di.repositoryModule
import co.id.cpn.domain.di.useCaseModule
import co.id.cpn.navsample.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { 
            androidContext(this@MyApp)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}