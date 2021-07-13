package co.id.cpn.domain

import androidx.lifecycle.LiveData
import co.id.cpn.entity.InventoryItem


interface InventoryRepository {
    fun getInventories(): LiveData<List<InventoryItem>>
    fun getInventory(id: Int): LiveData<InventoryItem>
    suspend fun insert(inventoryItem: InventoryItem)
    suspend fun delete(inventoryItem: InventoryItem)
    suspend fun update(inventoryItem: InventoryItem)
}