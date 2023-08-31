package com.ivansan.newsapp.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ivansan.newsapp.R
import com.ivansan.newsapp.data.converters.toShortString
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsPage(navController: NavController, sharedViewModel: SharedViewModel) {


    val item by sharedViewModel.sharedItem.observeAsState()
    var isSaved by mutableStateOf(item!!.favorite)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)),
                title = { /*TODO*/ },
                navigationIcon = {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.padding(6.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        ) {
                        Icon(modifier = Modifier.size(32.dp),
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color(0xFF03A9F4)
                        )
                    }
                    }
                },
                actions = {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.padding(6.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                    IconButton(onClick = {
                        isSaved = if(isSaved){
                            sharedViewModel.removeFromFavorite(item!!)
                            !isSaved
                        }else{
                            sharedViewModel.addToFavorite(item!!)
                            !isSaved
                        }
                    }) {
                            if(isSaved){
                                Icon(modifier = Modifier
                                    .size(32.dp),
                                    imageVector = Icons.Filled.Bookmark,
                                    contentDescription = "Add to saved",
                                    tint = Color(0xFF03A9F4)
                                )}
                            else {
                                Icon(modifier = Modifier
                                    .size(32.dp),
                                    imageVector = Icons.Filled.BookmarkBorder,
                                    contentDescription = "Add to saved",
                                    tint = Color(0xFF03A9F4)
                                )
                            }

                    }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0x0))
            )
        }
    ) {


    item?.let {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy((-24).dp)
            ) {


                Box(
                    modifier = Modifier.height(420.dp)
                ) {
                    
                    AsyncImage(
                        model = item!!.imageUrl,
                        contentDescription = item!!.title,
                        placeholder = painterResource(id = R.drawable.no_image_svg),
                        error = painterResource(id = R.drawable.no_image_svg),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    val gradient =
                        Brush.verticalGradient(listOf(Color(0x00000000), Color(0xF2000000)))

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .background(gradient)
                            .padding(top = 80.dp)
                    ) {
                        item!!.category?.let {
                            Text(
                                text = it.toShortString(),
                                modifier = Modifier

                                    .padding(horizontal = 16.dp)
                                    .background(Color(0xFF03A9F4), CircleShape)
                                    .padding(8.dp, 0.dp, 8.dp, 2.dp),
                                color = Color(0xFFFFFFFF),

                                )
                        }

                        Text(
                            text = item!!.title,
                            color = Color.White,
                            overflow = TextOverflow.Clip,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)

                        )


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 32.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = getDateDif(item!!.pubDate),
                                color = Color.White,
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp,
                                maxLines = 1
                                )
                            item!!.sourceId?.let {
                                Text(
                                    text = item!!.sourceId!!.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.ROOT
                                        ) else it.toString()
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        }


                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                        .background(MaterialTheme.colorScheme.background)

                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 32.dp),
                            text = item!!.content ?: "",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium

                        )

                    }
                }
            }


        }
    }
}


fun getDateDif(pubDate:String):String{

    val currentPubDate = LocalDateTime.parse(pubDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val currentDate = LocalDateTime.now()
    val daysDifValue = currentPubDate.until(currentDate, ChronoUnit.DAYS)
    val hoursDifValue = currentPubDate.until(currentDate, ChronoUnit.HOURS)
    val minutesDifValue = currentPubDate.until(currentDate, ChronoUnit.MINUTES)%60


     if (daysDifValue.toInt() == 1)
         return "Published $daysDifValue day ago"

     if (daysDifValue.toInt()>1)
        return "Published $daysDifValue days ago"

    return if (hoursDifValue.toInt()<0)
        "Published $minutesDifValue ago"
    else "Published $hoursDifValue hours and $minutesDifValue ago"

}




