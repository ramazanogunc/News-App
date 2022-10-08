package com.ramo.newsapp.ui.favorite

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.databinding.FragmentFavoriteBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(R.layout.fragment_favorite) {

    override fun init() {
        safeBinding {
            changeStatusBarColor(toolbar)
            rv.render { parent: ViewGroup, _: Int, _: News -> NewsViewHolder(parent) }
            rv.setOnItemClickListener { _, _, data: News ->
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToNewsDetailFragment(
                        data
                    )
                )
            }
        }
    }
}