package co.id.cpn.data.di

import co.id.cpn.data.InventoryRepositoryImpl
import co.id.cpn.domain.InventoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds // <-- class selain library @Provide <-- class yg dari/berhubungan dgn library
    internal abstract fun bindRepository(repositoryImpl: InventoryRepositoryImpl): InventoryRepository
    
}