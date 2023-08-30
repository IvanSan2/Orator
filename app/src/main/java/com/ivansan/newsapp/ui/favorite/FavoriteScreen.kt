package com.ivansan.newsapp.ui.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
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

    favoriteNews?.let {
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
}
}
