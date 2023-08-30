package com.ivansan.newsapp.ui.home


import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ivansan.newsapp.ui.composable.NewsCarousel
import com.ivansan.newsapp.ui.composable.CategoryTabRow
import com.ivansan.newsapp.ui.composable.SharedViewModel
import com.ivansan.newsapp.ui.settings.PREFERENCES_LANGUAGE


//comp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    sharedViewModel: SharedViewModel,
    preferences: SharedPreferences
) {

    val isSharedLanguage = !preferences.getString(PREFERENCES_LANGUAGE,null).isNullOrEmpty()

    val news by if (isSharedLanguage) viewModel.newsByLanguage.observeAsState() else viewModel.news.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!news.isNullOrEmpty()){
            NewsCarousel(list = news!!, navController = navController, sharedViewModel = sharedViewModel)
            CategoryTabRow(
                news!!,
                navController = navController,
                sharedViewModel = sharedViewModel,
                viewModel,
                preferences
            )
        }
    }
}
