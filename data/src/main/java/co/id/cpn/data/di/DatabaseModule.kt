package co.id.cpn.data.di

import androidx.room.Room
import co.id.cpn.data.local.InventoryRoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module { 
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("cpn".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), InventoryRoomDatabase::class.java,"inventory_database")
            .openHelperFactory(factory)
            .build()
    }
    single { 
        get<InventoryRoomDatabase>().inventoryDao()
    }
}
