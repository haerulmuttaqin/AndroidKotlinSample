package co.id.cpn.domain.usecase

import androidx.lifecycle.LiveData
import co.id.cpn.entity.InventoryItem
import co.id.cpn.domain.InventoryRepository
import javax.inject.Inject


class InventoryUseCase @Inject constructor(private val repository: InventoryRepository): InventoryInteractor {
    
    override fun getInventories(): LiveData<List<InventoryItem>> = repository.getInventories()
    override fun getInventory(id: Int): LiveData<InventoryItem> = repository.getInventory(id)
    override suspend fun insert(inventory: InventoryItem) = repository.insert(inventory)
    override suspend fun delete(inventory: InventoryItem) = repository.delete(inventory)
    override suspend fun update(inventory: InventoryItem) = repository.update(inventory)

}