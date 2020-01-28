package by.onexeor.data.source.remote.api

import by.onexeor.data.di.scopes.PerApplication
import by.onexeor.data.source.remote.model.products.ProductDTO
import by.onexeor.data.source.remote.model.products.ProductsWrapperDTO
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.api)
 */
@PerApplication
class ProductsService
@Inject constructor(@Named("retrofit") private val retrofit: Retrofit) : ProductsApi {

    private val mProductsApi by lazy { retrofit.create(ProductsApi::class.java) }

    override fun getProducts(): Call<ProductsWrapperDTO> =
        mProductsApi.getProducts()

    override fun getProduct(productId: String): Call<ProductDTO> =
        mProductsApi.getProduct(productId)
}