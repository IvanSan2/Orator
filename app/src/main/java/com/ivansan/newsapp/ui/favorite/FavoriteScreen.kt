package com.ivansan.newsapp.ui.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ivansan.newsapp.ui.composable.NewsListItem
import com.ivansan.newsapp.ui.composable.SharedViewModel

//comp
@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {

 val favoriteNews by viewModel.favoriteNews.observeAsState()

    if (!favoriteNews.isNullOrEmpty()) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp) ,
        contentPadding = PaddingValues(8.dp)
    ){
        itemsIndexed(favoriteNews!!){_, item ->
            NewsListItem(
                item = item,
                navController,
                sharedViewModel
            )
        }
    }
} else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(72.dp),
                imageVector = Icons.Default.Bookmarks,
                contentDescription = "Bookmarks",
                tint = MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "You`ve got nothing here" , fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
