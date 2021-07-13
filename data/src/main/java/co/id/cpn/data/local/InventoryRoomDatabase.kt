package co.id.cpn.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.id.cpn.entity.InventoryItem


@Database(entities = [InventoryItem::class], version = 1, exportSchema = false)
abstract class InventoryRoomDatabase: RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
    companion object {
        @Volatile
        private var INSTANCE: InventoryRoomDatabase? = null
        
        fun getDatabase(context: Context): InventoryRoomDatabase {
        
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, InventoryRoomDatabase::class.java,"inventory_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}