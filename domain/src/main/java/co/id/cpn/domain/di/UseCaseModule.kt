package co.id.cpn.domain.di

import co.id.cpn.domain.usecase.InventoryInteractor
import co.id.cpn.domain.usecase.InventoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    
    @Singleton
    @Binds
    internal abstract  fun bindUseCase(inventoryUseCase: InventoryUseCase): InventoryInteractor
}