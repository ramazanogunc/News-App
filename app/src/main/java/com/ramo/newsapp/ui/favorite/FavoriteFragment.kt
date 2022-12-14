package com.ramo.newsapp.ui.favorite

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.ext.safeContext
import com.ramo.newsapp.databinding.FragmentFavoriteBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import com.ramo.newsapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    R.layout.fragment_favorite
) {

    override fun isSharedViewModel(): Boolean = true

    override fun init() {
        safeBinding {
            rv.render { parent: ViewGroup, _: Int, _: News ->
                NewsViewHolder(
                    parent,
                    onFavClick = ::onFavClick
                )
            }
            rv.setOnItemClickListener { _, _, data: News ->
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToNewsDetailFragment(
                        data
                    )
                )
            }
        }
        observeExt(viewModel.news) {
            binding?.rv?.notifyDataSetChanged()
        }
        safeContext {
            viewModel.getFavorites(Utils.getDeviceId(it))
        }
    }

    override fun onResume() {
        super.onResume()
        safeBinding { changeStatusBarColor(toolbar) }
    }

    private fun onFavClick(news: News) {
        safeContext {
            viewModel.changeFavorite(Utils.getDeviceId(it), news)
        }
    }
}