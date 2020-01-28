package by.onexeor.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.onexeor.data.source.local.dao.ProductsDao
import by.onexeor.data.source.remote.model.products.ProductDTO

/**
 * Originally Created by onexeor (Savchik Viktor) on 28/01/2020
 * for TestTask (by.onexeor.data.source.local.db)
 */
@Database(
    entities = [
        ProductDTO::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TestAppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

}