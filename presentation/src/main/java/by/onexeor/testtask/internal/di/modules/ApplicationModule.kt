package by.onexeor.testtask.internal.di.modules

import android.content.Context
import android.net.ConnectivityManager
import by.onexeor.data.di.scopes.PerApplication
import by.onexeor.data.source.repository.ProductsRepository
import by.onexeor.domain.repositories.IProductsRepository
import dagger.Module
import dagger.Provides

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask.internal.di.modules)
 */
@Module
class ApplicationModule {


    @Provides
    @PerApplication
    fun provideConnectivityManager(context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    @Provides
    @PerApplication
    fun provideProductsRepository(source: ProductsRepository): IProductsRepository = source
}