package co.id.cpn.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import co.id.cpn.entity.InventoryItem

@Dao
interface InventoryDao {
    @Query("select * from InventoryItem order by id desc")
    fun getInventories(): LiveData<List<InventoryItem>>
    
    @Query("select * from InventoryItem where id=:id")
    fun getInventory(id: Int): LiveData<InventoryItem>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(inventoryItem: InventoryItem)
    
    @Delete
    suspend fun delete(inventoryItem: InventoryItem)
    
    @Update
    suspend fun update(inventoryItem: InventoryItem)
}