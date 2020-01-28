package by.onexeor.testtask.ui.component

import by.onexeor.testtask.R
import by.onexeor.testtask.ui.base.BaseActivity
import by.onexeor.testtask.ui.base.BaseFragment
import by.onexeor.testtask.ui.fragments.products.list.ProductsListFragment

class ProductListActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_product_list

    override fun fragment(): BaseFragment? = ProductsListFragment()
}
