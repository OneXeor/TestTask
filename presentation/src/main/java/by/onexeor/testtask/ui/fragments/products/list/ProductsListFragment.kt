package by.onexeor.testtask.ui.fragments.products.list

import android.content.Intent
import android.os.Bundle
import by.onexeor.domain.exception.Failure
import by.onexeor.testtask.R
import by.onexeor.testtask.extensions.failure
import by.onexeor.testtask.extensions.observe
import by.onexeor.testtask.extensions.viewModel
import by.onexeor.testtask.model.products.UIProductsWrapper
import by.onexeor.testtask.ui.base.BaseFragment
import by.onexeor.testtask.ui.component.PRODUCT_KEY
import by.onexeor.testtask.ui.component.ProductCartActivity
import kotlinx.android.synthetic.main.fragment_products_list.*

/**
 * TODO need to create and add empty state layout
 */
class ProductsListFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_products_list

    private lateinit var mViewModel: ProductsListViewModel

    private var mAdapter: ProductsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        mViewModel = viewModel(viewModelFactory) {
            observe(productsLiveData, ::handleProductsList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.showProgress()
        this.initUi()
        this.mViewModel.getProductsList()
    }

    private fun initUi() {
        mAdapter = ProductsListAdapter()
        mAdapter?.itemClickListener = { product ->
            startActivity(Intent(activity, ProductCartActivity::class.java).apply {
                putExtra(PRODUCT_KEY, product)
            })
        }
        rvProductsList.adapter = mAdapter
    }

    private fun handleProductsList(productsWrapper: UIProductsWrapper?) {
        super.hideProgress()
        productsWrapper?.let { mAdapter?.setData(it.products) }
    }


    private fun handleFailure(failure: Failure?) {
        super.hideProgress()
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_no_internet_connection)
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error)
            }
        }
    }
}
