package co.id.cpn.navsample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.cpn.domain.usecase.InventoryInteractor
import co.id.cpn.entity.InventoryItem
import kotlinx.coroutines.launch

class InventoryViewModel constructor(private val interactor: InventoryInteractor): ViewModel() {

    val inventoryItems: LiveData<List<InventoryItem>> = interactor.getInventories()
    
    fun addNewItem(itemName: String, itemPrice: String, quantityInStock: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, quantityInStock)
        insertRepository(newItem)
    }

    private fun getNewItemEntry(
        itemName: String,
        itemPrice: String,
        quantityInStock: String
    ): InventoryItem {
        return InventoryItem(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = quantityInStock.toInt()
        )
    }

    fun getInventory(id: Int): LiveData<InventoryItem> = interactor.getInventory(id)
    
    fun sellInventory(inventory: InventoryItem) {
        viewModelScope.launch { 
            interactor.sell(inventory)
        }
    }

    fun updateInventory(id: Int, itemName: String, itemPrice: String, quantityInStock: String) {
        val updateInventory = InventoryItem(
            id = id,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = quantityInStock.toInt()
        )
        update(updateInventory)
    }
    
    private fun insertRepository(inventory: InventoryItem) {
        viewModelScope.launch {
            interactor.insert(inventory)
        }
    }
    
    private fun update(inventory: InventoryItem) {
        viewModelScope.launch {
            interactor.update(inventory)
        }
    }
    
    fun deleteInventory(inventory: InventoryItem) {
        viewModelScope.launch {
            interactor.delete(inventory)
        }
    }
    
}