package co.id.cpn.navsample.presentation.di

import co.id.cpn.navsample.presentation.InventoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module { 
    viewModel { InventoryViewModel(get()) }
}