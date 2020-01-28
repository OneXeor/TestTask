package by.onexeor.testtask.ui.fragments.products.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.onexeor.testtask.R
import by.onexeor.testtask.extensions.inflate
import by.onexeor.testtask.extensions.loadFromUrl
import by.onexeor.testtask.model.products.UIProduct
import by.onexeor.testtask.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.abc_item_product.view.*

/**
 * Originally Created by onexeor (Savchik Viktor) on 28/01/2020
 * for TestTask (by.onexeor.testtask.ui.fragments.products.list)
 */
class ProductsListAdapter : BaseAdapter<UIProduct>() {

    var itemClickListener: ((UIProduct) -> Unit?)? = null

    override fun onCreateDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ProductHolder(parent.inflate(R.layout.abc_item_product))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductHolder) items?.get(position)?.let { holder.bind(it) }
    }

    inner class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: UIProduct) {
            item.image?.let { itemView.ivProductImage.loadFromUrl(it, .3F) }
            itemView.tvProductName.text =
                item.name ?: itemView.resources.getString(R.string.unknown)
            itemView.tvProductPrice.text =
                item.formattedPrice ?: itemView.resources.getString(R.string.unknown)
            itemView.clContent.setOnClickListener { itemClickListener?.invoke(item) }
        }
    }
}