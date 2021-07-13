package co.id.cpn.domain.usecase

import androidx.lifecycle.LiveData
import co.id.cpn.entity.InventoryItem


interface InventoryInteractor {
    fun getInventories(): LiveData<List<InventoryItem>>
    fun getInventory(id: Int): LiveData<InventoryItem>
    suspend fun insert(inventory: InventoryItem)
    suspend fun delete(inventory: InventoryItem)
    suspend fun update(inventory: InventoryItem)
    suspend fun sell(inventory: InventoryItem)
}