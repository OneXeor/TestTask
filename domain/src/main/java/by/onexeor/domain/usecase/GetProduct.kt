package by.onexeor.domain.usecase

import by.onexeor.domain.UseCase
import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import by.onexeor.domain.model.products.FullProduct
import by.onexeor.domain.repositories.IProductsRepository
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.domain.usecase)
 */
class GetProduct @Inject constructor(
    private val productsRepository: IProductsRepository
) : UseCase<FullProduct, String>() {

    override
    suspend
    fun run(params: String): Either<Failure, FullProduct> =
        productsRepository.getProductById(params)
}