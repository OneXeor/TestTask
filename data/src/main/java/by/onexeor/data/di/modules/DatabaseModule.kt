package by.onexeor.data.di.modules

import android.content.Context
import androidx.room.Room
import by.onexeor.data.di.scopes.PerApplication
import by.onexeor.data.source.local.db.TestAppDatabase
import dagger.Module
import dagger.Provides

/**
 * Originally Created by onexeor (Savchik Viktor) on 28/01/2020
 * for TestTask (by.onexeor.data.di.modules)
 */
@Module
class DatabaseModule(private val dbName: String) {

    @Provides
    @PerApplication
    fun provideDatabase(context: Context): TestAppDatabase {
        return Room.databaseBuilder(context, TestAppDatabase::class.java, dbName).build()
    }
}