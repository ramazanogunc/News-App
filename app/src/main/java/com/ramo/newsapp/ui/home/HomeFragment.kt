package com.ramo.newsapp.ui.home

import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.ext.safeContext
import com.ramo.newsapp.databinding.FragmentHomeBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import com.ramo.newsapp.ui.favorite.FavoriteViewModel
import com.ramo.newsapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private val favViewModel by activityViewModels<FavoriteViewModel>()

    override fun init() {
        safeBinding {
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
            if (viewModel.news.value.isNullOrEmpty().not()) {
                favViewModel.detectFavoriteStatus(viewModel.news.value!!)
                binding?.rv?.notifyDataSetChanged()
            }
        }
        observeExt(viewModel.news) {
            if (favViewModel.news.value.isNullOrEmpty().not()) {
                favViewModel.detectFavoriteStatus(it)
                binding?.rv?.notifyDataSetChanged()
            }
        }
        safeContext {
            viewModel.deviceId = Utils.getDeviceId(it)
            favViewModel.getFavorites(viewModel.deviceId)
        }
    }

    override fun onResume() {
        super.onResume()
        safeBinding { changeStatusBarColor(toolbar) }
    }

    private fun onFavClick(news: News) {
        val ct = context ?: return
        favViewModel.changeFavorite(Utils.getDeviceId(ct), news)
    }
}