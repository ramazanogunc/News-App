package com.ramo.newsapp.ui.newsdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramo.newsapp.core.BaseComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseComposeFragment<NewsDetailViewModel>() {

    override fun content(): ComposeView = composableView {
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = viewModel.news.imageUrl,
                    contentDescription = null
                )
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
        }
    }

}
