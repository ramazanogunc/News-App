package com.ramo.newsapp.ui

import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseActivity
import com.ramo.newsapp.core.ext.gone
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.ext.visible
import com.ramo.newsapp.databinding.ActivityMainBinding
import com.ramo.newsapp.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun init() {
        safeBinding {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.newsDetailFragment) {
                    bottomNav.gone()
                } else {
                    bottomNav.visible()
                }
            }
        }

        observeExt(favoriteViewModel.news) {
            val badge =
                binding?.bottomNav?.getOrCreateBadge(R.id.favoriteFragment) ?: return@observeExt
            badge.isVisible = true
            badge.number = it.size
        }
    }
}