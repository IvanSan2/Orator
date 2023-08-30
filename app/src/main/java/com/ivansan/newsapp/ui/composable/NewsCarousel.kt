package com.ivansan.newsapp.ui.composable



import android.annotation.SuppressLint
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ivansan.newsapp.R
import com.ivansan.newsapp.data.converters.toShortString
import com.ivansan.newsapp.data.entity.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,)
@Composable
fun NewsCarousel(
    list: List<Result>,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { 10 })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {

            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                        delay(timeMillis = 5000L)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {

                HorizontalPager(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                        .align(Alignment.TopCenter),
                    state = pagerState,
                    contentPadding = PaddingValues(
                        horizontal = 32.dp),
                    pageSpacing = 16.dp
                )
                { index ->


                    val item: Result = list[index]

                    ElevatedCard(modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .graphicsLayer {
                            val pageOffset =
                                ((pagerState.currentPage - index) + pagerState.currentPageOffsetFraction).absoluteValue

                            val transformation = lerp(
                                start = 0.8f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleY = transformation
                        },

                        onClick = {
                            sharedViewModel.addResult(item)
                            navController.navigate("news_page")
                        }
                    ) {
                        CarouselItem(item)

                    }



                }

        }
        PagerIndicator(
            Modifier.padding(8.dp),
            pageCount = 10,
            currentPage = pagerState.currentPage
        )
    }
}





@Composable
fun CarouselItem(item: Result) {
    Box {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            placeholder = painterResource(id = R.drawable.no_image_svg),
            error = painterResource(id = R.drawable.no_image_svg),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        item.category?.let {
            Text(
                text = it.toShortString(),
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xFF03A9F4), CircleShape)
                    .padding(8.dp, 0.dp, 8.dp, 2.dp),
                color = Color(0xFFFFFFFF),

            )
        }

        val gradient =
            Brush.verticalGradient(listOf(Color(0x00000000), Color(0xA9000000), Color(0xF2000000)))

        Text(
            text = item.title,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Clip,
            fontWeight = FontWeight.Bold,

            textAlign = TextAlign.Center ,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp)

        )
    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
    ) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) Color(0xFF03A9F4)  else Color(0xFFA1DFFC)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(if (currentPage == iteration) 8.dp else 6.dp)
            )
        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}