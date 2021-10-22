package com.example.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.presentation.BaseFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    fun <T : ViewModel> getViewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as BibleApp).getViewModel(model, owner)

    fun factory() = (application as BibleApp).factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        //содержит в себе ViewModelStore который является оберткой над HashMap
        // в который по ключу ложим вьюмодельку
        viewModel = getViewModel(MainViewModel::class.java, this)

        viewModel.observe(this) {
            navigate(viewModel.getFragment(it))
        }
        viewModel.init(savedInstanceState == null)
    }

    private fun navigate(fragment: BaseFragment<*>) = with(supportFragmentManager) {
        if (canReplace(fragment))
            beginTransaction()
                .replace(R.id.container, fragment, fragment.name())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        if (item.itemId == R.id.languages) {
            navigate(viewModel.getLanguagesScreen())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}

private fun FragmentManager.canReplace(fragment: BaseFragment<*>) =
    fragments.isEmpty() || !(fragments.last() as BaseFragment<*>).matches(fragment.name())
