package com.ramo.newsapp.ui.newsdetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.sharp.OpenInBrowser
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import coil.compose.AsyncImage
import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseComposeFragment
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.ui.favorite.FavoriteViewModel
import com.ramo.newsapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailFragment : BaseComposeFragment<NewsDetailViewModel>() {

    private val favoriteViewModel by activityViewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeExt(favoriteViewModel.news) { viewModel.checkFavStatus(it) }
    }

    override fun content(): ComposeView = createComposeView {
        MaterialTheme {
            Scaffold(
                floatingActionButton = {
                    Column {
                        val ct = LocalContext.current
                        FloatingActionButton(onClick = {
                            favoriteViewModel.changeFavorite(Utils.getDeviceId(ct), viewModel.news)
                        }) {
                            Icon(
                                imageVector = if (viewModel.isFavorite)
                                    Icons.Rounded.Bookmark
                                else
                                    Icons.Rounded.BookmarkBorder,
                                contentDescription = stringResource(id = R.string.cd_news_fav_btn)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        FloatingActionButton(onClick = {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, viewModel.news.url.toUri())
                            startActivity(browserIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Sharp.OpenInBrowser,
                                contentDescription = stringResource(id = R.string.cd_open_link)
                            )
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
                            contentDescription = stringResource(id = R.string.cd_news),
                            contentScale = ContentScale.Crop
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
