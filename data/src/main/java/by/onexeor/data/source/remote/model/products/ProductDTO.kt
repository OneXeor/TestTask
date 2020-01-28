package by.onexeor.data.source.remote.model.products

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.onexeor.domain.model.products.FullProduct
import by.onexeor.domain.model.products.Product
import com.google.gson.annotations.SerializedName


/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.model)
 */
@Entity
data class ProductDTO(
    val image: String? = null,
    val price: Int? = null,
    @PrimaryKey
    @NonNull
    @SerializedName("product_id")
    val productId: String,
    val name: String? = null,
    val description: String? = null
) {

    fun toFullProduct() = FullProduct(image, price, productId, name, description)

    fun toProduct() = Product(image, price, productId, name)

    companion object {
        fun empty() = ProductDTO(productId = "")
    }
}