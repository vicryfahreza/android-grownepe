package com.vicry.grownepe.ui.screen.lms.detailLms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.vicry.grownepe.model.repo.NepenthesInjection
import com.vicry.grownepe.ui.factory.ViewModelFactory
import com.vicry.grownepe.ui.state.UIStatus
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@Composable
fun DetailLmsScreen (
    lmsId: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailLmsModel = viewModel(
        factory = ViewModelFactory(
            NepenthesInjection.provideRepository()
        )
    )
) {
    Scaffold { padding ->
        Box(modifier = modifier.padding(padding)) {
            viewModel.uiStatus.collectAsState(initial = UIStatus.Loading).value.let { uiStatus ->
                when (uiStatus) {
                    is UIStatus.Loading -> {
                        viewModel.getLMSById(lmsId)
                    }
                    is UIStatus.Success -> {
                        val detail = uiStatus.data
                        DetailContent(
                            detail.lms.imageBackground,
                            detail.lms.title,
                            detail.lms.description1,
                            detail.lms.description2,
                            detail.lms.videoSrc,
                            detail.lms.videoId
                        )
                    }
                    is UIStatus.Error -> {}
                }
            }
        }
    }
}

@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}

@Composable
fun DetailContent(
    image: String,
    name: String,
    description1: String,
    description2: String,
    videoSrc: String,
    videoId: String,
    modifier: Modifier = Modifier,
) {

    Box {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState())
        ){
            AsyncImage(model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = description1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    modifier = modifier
                        .padding(10.dp)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = description2,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                YoutubeScreen(videoId = videoId, modifier = Modifier)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = videoSrc,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

        }
    }




}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    GrowNepeTheme {
        DetailContent(
            image = "",
            name = "Echeveria",
            description1 = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
            description2 = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
            videoSrc = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
            videoId = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
            )
    }
}