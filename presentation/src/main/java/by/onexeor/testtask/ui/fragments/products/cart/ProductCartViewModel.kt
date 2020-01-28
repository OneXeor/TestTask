package by.onexeor.testtask.ui.fragments.products.cart

import androidx.lifecycle.MutableLiveData
import by.onexeor.domain.model.products.FullProduct
import by.onexeor.domain.model.products.Product
import by.onexeor.domain.usecase.GetProduct
import by.onexeor.testtask.model.products.UIFullProduct
import by.onexeor.testtask.model.products.UIProduct
import by.onexeor.testtask.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask.ui.fragments.products.cart)
 */
class ProductCartViewModel @Inject constructor(
    private val getProduct: GetProduct
) : BaseViewModel() {

    val productCartLiveData = MutableLiveData<UIFullProduct>()

    fun getProductDetails(productId: String) = getProduct(productId) {
        it.either(
            ::handleFailure,
            ::handleProduct
        )
    }

    private fun handleProduct(product: FullProduct) {
        productCartLiveData.value = UIFullProduct(
            name = product.name,
            description = product.description,
            productId = product.productId,
            price = product.price,
            image = product.image,
            formattedPrice = "$" + product.price
        )
    }
}