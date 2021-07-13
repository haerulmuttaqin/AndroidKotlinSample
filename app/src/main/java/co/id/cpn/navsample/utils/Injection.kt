package co.id.cpn.navsample.utils

import android.content.Context
import co.id.cpn.data.InventoryRepositoryImpl
import co.id.cpn.data.local.InventoryRoomDatabase
import co.id.cpn.domain.InventoryRepository
import co.id.cpn.domain.usecase.InventoryInteractor
import co.id.cpn.domain.usecase.InventoryUseCase

object Injection {
    
    fun provideRepository(context: Context): InventoryRepository {
        val database = InventoryRoomDatabase.getDatabase(context)
        return InventoryRepositoryImpl(database.inventoryDao())
    }
    
    fun provideInteractor(context: Context): InventoryInteractor {
        val repository = provideRepository(context)
        return InventoryUseCase(repository)
    }
}