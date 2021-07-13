package co.id.cpn.data.di

import androidx.room.Room
import co.id.cpn.data.local.InventoryRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module { 
    single {
        Room.databaseBuilder(androidContext(), InventoryRoomDatabase::class.java,"inventory_database").build()
    }
    single { 
        get<InventoryRoomDatabase>().inventoryDao()
    }
}
