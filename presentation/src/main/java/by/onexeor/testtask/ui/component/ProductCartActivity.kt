package by.onexeor.testtask.ui.component

import by.onexeor.testtask.R
import by.onexeor.testtask.model.products.UIProduct
import by.onexeor.testtask.ui.base.BaseActivity
import by.onexeor.testtask.ui.base.BaseFragment
import by.onexeor.testtask.ui.fragments.products.cart.ProductCartFragment

const val PRODUCT_KEY = "PRODUCT_KEY"

class ProductCartActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_product_cart

    override fun fragment(): BaseFragment? =
        ProductCartFragment.newInstance(intent.getParcelableExtra(PRODUCT_KEY) ?: UIProduct())
}
