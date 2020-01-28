package by.onexeor.testtask

import android.app.Application
import by.onexeor.data.di.modules.DatabaseModule
import by.onexeor.data.di.modules.NetworkModule
import by.onexeor.testtask.internal.di.components.ApplicationComponent
import by.onexeor.testtask.internal.di.components.DaggerApplicationComponent

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.testtask)
 *
 * TODO need to cover the Application with tests
 */
class TestApplication : Application() {

    private val database by lazy { DatabaseModule("test_app") }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule(this))
            .databaseModule(database)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}