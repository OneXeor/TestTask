package by.onexeor.data.source.repository

import by.onexeor.data.platform.NetworkHandler
import by.onexeor.data.source.local.db.TestAppDatabase
import by.onexeor.data.source.remote.RemoteProcessor
import by.onexeor.data.source.remote.api.ProductsService
import by.onexeor.data.source.remote.model.products.ProductDTO
import by.onexeor.data.source.remote.model.products.ProductsWrapperDTO
import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import by.onexeor.domain.model.products.FullProduct
import by.onexeor.domain.model.products.ProductsWrapper
import by.onexeor.domain.repositories.IProductsRepository
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.repositories)
 */
class ProductsRepository @Inject constructor(
    private val remoteProcessor: RemoteProcessor,
    private val testAppDatabase: TestAppDatabase,
    private val networkHandler: NetworkHandler,
    private val productsService: ProductsService
) : IProductsRepository {

    override fun getProducts(): Either<Failure, ProductsWrapper> {
        return if (networkHandler.isConnected)
            remoteProcessor.processRequest(
                call = productsService.getProducts(),
                transform = {
                    testAppDatabase.productsDao().insertAll(it.products); it.toProducts()
                },
                default = ProductsWrapperDTO.empty()
            ) else Either.Right(
            ProductsWrapper(products = testAppDatabase.productsDao()[0, 100]?.map { it?.toProduct() })
        )
    }

    override fun getProductById(productId: String): Either<Failure, FullProduct> {
        return if (networkHandler.isConnected)
            remoteProcessor.processRequest(
                call = productsService.getProduct(productId),
                transform = { testAppDatabase.productsDao().insertAll(it); it.toFullProduct() },
                default = ProductDTO.empty()
            )
        else Either.Right(testAppDatabase.productsDao().getById(productId).toFullProduct())

    }
}