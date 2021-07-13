package co.id.cpn.data

import androidx.lifecycle.LiveData
import co.id.cpn.data.local.InventoryDao
import co.id.cpn.domain.InventoryRepository
import co.id.cpn.entity.InventoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepositoryImpl constructor(private val dao: InventoryDao): InventoryRepository {

    override fun getInventories(): LiveData<List<InventoryItem>> = dao.getInventories()
    override fun getInventory(id: Int): LiveData<InventoryItem> = dao.getInventory(id)
    override suspend fun insert(inventoryItem: InventoryItem) {
        withContext(Dispatchers.IO) { dao.insert(inventoryItem) }
    }
    override suspend fun delete(inventoryItem: InventoryItem) {
        withContext(Dispatchers.IO) { dao.delete(inventoryItem) }
    }
    override suspend fun update(inventoryItem: InventoryItem) {
        withContext(Dispatchers.IO) { dao.update(inventoryItem) }
    }
}