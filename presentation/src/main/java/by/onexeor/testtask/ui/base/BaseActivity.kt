package by.onexeor.testtask.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.onexeor.testtask.R
import by.onexeor.testtask.extensions.inTransaction

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        fragment()?.let {
            (supportFragmentManager.findFragmentById(
                R.id.fragmentContainer
            ) as BaseFragment).onBackPressed()
        }
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        fragment()?.let {
            savedInstanceState ?: supportFragmentManager.inTransaction {
                add(
                    R.id.fragmentContainer, it
                )
            }
        }

    abstract fun fragment(): BaseFragment?
}
