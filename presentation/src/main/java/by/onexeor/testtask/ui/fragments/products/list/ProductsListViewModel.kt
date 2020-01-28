package by.onexeor.testtask.ui.fragments.products.list

import androidx.lifecycle.MutableLiveData
import by.onexeor.domain.model.products.ProductsWrapper
import by.onexeor.domain.usecase.GetProducts
import by.onexeor.testtask.model.products.UIProduct
import by.onexeor.testtask.model.products.UIProductsWrapper
import by.onexeor.testtask.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask.ui.fragments)
 */
class ProductsListViewModel
@Inject constructor(val getProducts: GetProducts) : BaseViewModel() {

    val productsLiveData = MutableLiveData<UIProductsWrapper>()

    fun getProductsList() = getProducts(null) { it.either(::handleFailure, ::handleProductsList) }

    private fun handleProductsList(productsWrapper: ProductsWrapper) {
        // TODO Need to create mapper and resource provider
        val uiProductsWrapper = UIProductsWrapper(ArrayList<UIProduct>().apply {
            productsWrapper.products?.forEach {
                add(
                    UIProduct(
                        productId = it?.productId,
                        image = it?.image,
                        price = it?.price,
                        formattedPrice = "$" + it?.price,
                        name = it?.name
                    )
                )
            }
        })
        productsLiveData.value = uiProductsWrapper
    }
}