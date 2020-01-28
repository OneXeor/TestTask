package by.onexeor.data.source.local.dao

import androidx.room.*
import by.onexeor.data.source.remote.model.products.ProductDTO


/**
 * Originally Created by onexeor (Savchik Viktor) on 28/01/2020
 * for TestTask (by.onexeor.data.source.local.repositories)
 */
@Dao
interface ProductsDao {

    @Query("SELECT * FROM ProductDTO LIMIT :limit OFFSET :offset")
    operator fun get(offset: Int, limit: Int): List<ProductDTO?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductDTO?>?): LongArray?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: ProductDTO?): LongArray?

    @Query("SELECT * FROM ProductDTO WHERE productId == :productId")
    fun getById(productId: String): ProductDTO

    @Delete
    fun delete(products: ProductDTO?)

    @Query("DELETE FROM ProductDTO")
    fun deleteAll()
}