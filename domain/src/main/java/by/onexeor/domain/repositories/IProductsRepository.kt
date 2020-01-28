package by.onexeor.domain.repositories

import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import by.onexeor.domain.model.products.FullProduct
import by.onexeor.domain.model.products.ProductsWrapper

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.repositories)
 */
interface IProductsRepository {

    fun getProducts(): Either<Failure, ProductsWrapper>

    fun getProductById(productId: String): Either<Failure, FullProduct>
}