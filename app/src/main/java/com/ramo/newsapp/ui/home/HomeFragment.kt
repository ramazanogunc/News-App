package com.ramo.newsapp.ui.home

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.databinding.FragmentHomeBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun init() {
        safeBinding {
            changeStatusBarColor(toolbar)
            rv.render { parent: ViewGroup, _: Int, _: News -> NewsViewHolder(parent) }
            rv.setOnItemClickListener { _, _, data: News ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment2(
                        data
                    )
                )
            }
        }
    }
}