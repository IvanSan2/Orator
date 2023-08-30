package com.ivansan.newsapp.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ivansan.newsapp.data.mapper.toResult
import com.ivansan.newsapp.ui.composable.NewsListItem
import com.ivansan.newsapp.ui.composable.SharedViewModel

//comp
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController = rememberNavController(),
    sharedViewModel: SharedViewModel
    ) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val news by viewModel.searchNews.observeAsState()

    //TODO: add search history from db
    //var historyItems = remember {}

    Column {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            query = text,
            onQueryChange = {
                            text = it
            },
            onSearch = {
                       active = false
                        viewModel.searchNews(it)
            },
            active = false, // TODO: add history search and change to active
            onActiveChange ={
                active = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
            },
            trailingIcon = {
                Icon(modifier = Modifier.clickable {
                        text = ""
                        active = false
                },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon")
            },
        ) {

        }
        news?.let{
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp) ,
                contentPadding = PaddingValues(8.dp)
            ){
                itemsIndexed(news!!){ _, item ->
                    NewsListItem(
                        item = item.toResult(),
                        navController,
                        sharedViewModel
                    )
                }
            }
        }
    }
}
