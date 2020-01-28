package by.onexeor.data.source.remote.api

import by.onexeor.data.source.remote.model.products.ProductDTO
import by.onexeor.data.source.remote.model.products.ProductsWrapperDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.controllers)
 */
internal interface ProductsApi {

    /**
     * Get products list
     *
     * @return [ProductsWrapper]
     */
    @GET("/developer-application-test/cart/list")
    fun getProducts(): Call<ProductsWrapperDTO>


    /**
     * Get product info by id
     *
     * @param productId
     *
     * @return [FullProduct]
     */
    @GET("/developer-application-test/cart/{id}/detail")
    fun getProduct(@Path(value = "id") productId: String): Call<ProductDTO>

}