package co.id.cpn.domain.di

import co.id.cpn.domain.usecase.InventoryInteractor
import co.id.cpn.domain.usecase.InventoryUseCase
import org.koin.dsl.module

val useCaseModule = module { 
    single<InventoryInteractor> {
        InventoryUseCase(get())
    }
}