package by.onexeor.data.source.remote.model.products

import by.onexeor.domain.model.products.Product
import by.onexeor.domain.model.products.ProductsWrapper

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.model)
 *
 * contain list of [Product]
 */
data class ProductsWrapperDTO(
    val products: List<ProductDTO?>? = null
) {

    fun toProducts() = ProductsWrapper(ArrayList<Product>().apply {
        products?.filterNotNull()?.forEach { this.add(it.toProduct()) }
    })

    companion object {
        fun empty() =
            ProductsWrapperDTO(
                emptyList()
            )
    }
}
