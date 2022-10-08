package com.ramo.newsapp.ui.home

import android.content.res.Resources
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.color.MaterialColors
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.databinding.FragmentHomeBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.ui.common.viewholder.NewsViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {


    override fun init() {
        changeStatusBarColor()
        safeBinding {
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

    override fun onDestroyView() {
        removeStatusBarColor()
        super.onDestroyView()
    }

    private fun changeStatusBarColor() = safeBinding {
        val color = MaterialColors.getColor(toolbar, androidx.appcompat.R.attr.colorPrimary)
        val window = activity?.window ?: return@safeBinding
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        @Suppress("DEPRECATION")
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color
    }

    private fun removeStatusBarColor()  {
        val window = activity?.window ?: return
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}