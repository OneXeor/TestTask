package by.onexeor.testtask.internal.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.onexeor.testtask.ui.fragments.products.cart.ProductCartViewModel
import by.onexeor.testtask.ui.fragments.products.list.ProductsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Originally Created by onexeor (Savchik Viktor) on 11/10/2019
 * for ObtainCare
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductsListViewModel::class)
    internal abstract fun bindsProductsListViewModel(viewModel: ProductsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductCartViewModel::class)
    internal abstract fun bindsProductCartViewModel(viewModel: ProductCartViewModel): ViewModel

}