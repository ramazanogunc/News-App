package com.ramo.newsapp.ui.home

import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.databinding.FragmentHomeBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import com.ramo.newsapp.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private val favViewModel by activityViewModels<FavoriteViewModel>()

    override fun init() {
        safeBinding {
            changeStatusBarColor(toolbar)
            rv.render { parent: ViewGroup, _: Int, _: News ->
                NewsViewHolder(
                    parent,
                    onFavClick = ::onFavClick
                )
            }
            rv.onScrollEnd {
                rv.isPaginationEnable = false
                viewModel.nextNews()
            }
            rv.setOnItemClickListener { _, _, data: News ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment2(
                        data
                    )
                )
            }
        }

        observeExt(favViewModel.news) {
            binding?.rv?.notifyDataSetChanged()
        }
    }

    private fun onFavClick(news: News) {
        favViewModel.changeFavorite(news)
    }
}