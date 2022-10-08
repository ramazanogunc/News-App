package com.ramo.newsapp.ui.newsdetail

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.OpenInBrowser
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import coil.compose.AsyncImage
import com.ramo.newsapp.core.BaseComposeFragment
import com.ramo.newsapp.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailFragment : BaseComposeFragment<NewsDetailViewModel>() {

    private val favoriteViewModel by activityViewModels<FavoriteViewModel>()

    override fun content(): ComposeView = createComposeView {
        MaterialTheme {
            Scaffold(
                floatingActionButton = {
                    Column {
                        FloatingActionButton(onClick = {
                            favoriteViewModel.addFavorite(viewModel.news)
                        }) {
                            Icon(imageVector = Icons.Sharp.Favorite, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        FloatingActionButton(onClick = {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, viewModel.news.url.toUri())
                            startActivity(browserIntent)
                        }) {
                            Icon(imageVector = Icons.Sharp.OpenInBrowser, contentDescription = "")
                        }
                    }
                }
            ) {

                Spacer(modifier = Modifier.size(it.calculateBottomPadding()))
                LazyColumn {
                    item {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = viewModel.news.imageUrl,
                            contentDescription = null
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color.Green, shape = RoundedCornerShape(3.dp))
                                    .width(6.dp)
                                    .fillMaxHeight()
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                text = viewModel.news.title,
                                style = MaterialTheme.typography.h5
                            )
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = viewModel.news.content,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

        }
    }
}
