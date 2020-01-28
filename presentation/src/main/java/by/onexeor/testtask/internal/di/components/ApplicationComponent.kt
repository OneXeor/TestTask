package by.onexeor.testtask.internal.di.components

import by.onexeor.data.di.modules.DatabaseModule
import by.onexeor.data.di.modules.NetworkModule
import by.onexeor.data.di.scopes.PerApplication
import by.onexeor.testtask.TestApplication
import by.onexeor.testtask.internal.di.modules.ApplicationModule
import by.onexeor.testtask.internal.di.viewModel.ViewModelModule
import by.onexeor.testtask.ui.fragments.products.cart.ProductCartFragment
import by.onexeor.testtask.ui.fragments.products.list.ProductsListFragment
import dagger.Component

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask.internal)
 */
@PerApplication
@Component(
    modules = [
        NetworkModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationComponent {


    fun inject(application: TestApplication)

    fun inject(application: ProductsListFragment)

    fun inject(productCartFragment: ProductCartFragment)

}