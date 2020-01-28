package by.onexeor.testtask.ui.fragments.products.cart


import android.os.Bundle
import by.onexeor.domain.exception.Failure
import by.onexeor.testtask.R
import by.onexeor.testtask.extensions.*
import by.onexeor.testtask.model.products.UIFullProduct
import by.onexeor.testtask.model.products.UIProduct
import by.onexeor.testtask.ui.base.BaseFragment
import by.onexeor.testtask.ui.component.PRODUCT_KEY
import kotlinx.android.synthetic.main.fragment_product_cart.*

/**
 *
 */
class ProductCartFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_product_cart

    private lateinit var mViewModel: ProductCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        mViewModel = viewModel(viewModelFactory) {
            observe(productCartLiveData, ::handleProduct)
            failure(failure, ::handleFailure)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val product = arguments?.getParcelable<UIProduct>(PRODUCT_KEY)
        product?.productId?.let { mViewModel.getProductDetails(it) } ?: close()
    }

    private fun handleProduct(uiProduct: UIFullProduct?) {
        uiProduct?.image?.let { ivProductImage.loadFromUrl(it) }
        tvProductName.text = uiProduct?.name
        tvProductPrice.text = uiProduct?.formattedPrice
        tvProductDescription.text = uiProduct?.description
    }


    private fun handleFailure(failure: Failure?) {
        super.hideProgress()
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_no_internet_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
        }
    }

    companion object {
        fun newInstance(product: UIProduct) =
            ProductCartFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PRODUCT_KEY, product)
                }
            }
    }

}
