package co.id.cpn.data.di

import android.content.Context
import androidx.room.Room
import co.id.cpn.data.local.InventoryDao
import co.id.cpn.data.local.InventoryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    
    @Singleton //<-- provide once 
    @Provides  //<-- inject a class from 3rd
    internal fun provideDatabase(@ApplicationContext context: Context): InventoryRoomDatabase {
        return Room.databaseBuilder(context.applicationContext, InventoryRoomDatabase::class.java,"inventory_database").build()
    }
    
    @Singleton
    @Provides
    internal fun provideDao(database: InventoryRoomDatabase): InventoryDao {
        return database.inventoryDao()
    }
    
}