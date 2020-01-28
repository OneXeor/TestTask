package by.onexeor.domain.usecase

import by.onexeor.domain.UseCase
import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import by.onexeor.domain.model.products.ProductsWrapper
import by.onexeor.domain.repositories.IProductsRepository
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.domain.usecase)
 */
class GetProducts @Inject constructor(
    private val productsRepository: IProductsRepository
) : UseCase<ProductsWrapper, Any?>() {

    override
    suspend
    fun run(params: Any?): Either<Failure, ProductsWrapper> = productsRepository.getProducts()
}