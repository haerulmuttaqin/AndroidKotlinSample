package co.id.cpn.data.di

import co.id.cpn.data.InventoryRepositoryImpl
import co.id.cpn.domain.InventoryRepository
import org.koin.dsl.module

val repositoryModule = module { 
    single<InventoryRepository> { 
        InventoryRepositoryImpl(get())
    }
}