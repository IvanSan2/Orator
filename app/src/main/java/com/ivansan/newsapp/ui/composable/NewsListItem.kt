package com.ivansan.newsapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.ivansan.newsapp.data.entity.Result

@Composable
fun NewsListItem(
    item: Result,
    navController: NavController,
    sharedViewModel: SharedViewModel
){
Row(modifier = Modifier
    .height(114.dp)
    .clip(RoundedCornerShape(8.dp))
    .background(MaterialTheme.colorScheme.surfaceVariant)
    .padding(8.dp)
    .clickable {
        sharedViewModel.addResult(item)
        navController.navigate("news_page")
    }
) {
    AsyncImage(model =item.imageUrl,
        contentDescription = "Image",
        placeholder = painterResource(id = R.drawable.no_image_svg),
        error = painterResource(id = R.drawable.no_image_svg),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(96.dp)
            .clip(RoundedCornerShape(8.dp))
        )
    Box{
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp)
           ) {
        Text(text = item.category!!.toShortString(),
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
            )
        Text(text = item.title,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,


        ) {
            Text(text = item.sourceId?: "",
                fontSize = 14.sp
            )
            Text(text = item.pubDate,
                fontSize = 14.sp,
                textAlign = TextAlign.End
            )
        }
    }
}
}



