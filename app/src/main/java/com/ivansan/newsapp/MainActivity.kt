package com.ivansan.newsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivansan.newsapp.ui.composable.NewsPage
import com.ivansan.newsapp.ui.composable.SharedViewModel
import com.ivansan.newsapp.ui.favorite.FavoriteScreen
import com.ivansan.newsapp.ui.favorite.FavoriteViewModel
import com.ivansan.newsapp.ui.home.HomeScreen
import com.ivansan.newsapp.ui.home.HomeViewModel
import com.ivansan.newsapp.ui.navbar.NavigationBarM3
import com.ivansan.newsapp.ui.search.SearchScreen
import com.ivansan.newsapp.ui.search.SearchViewModel
import com.ivansan.newsapp.ui.settings.PREFERENCES_API_KEY
import com.ivansan.newsapp.ui.settings.PREFERENCES_SETTINGS
import com.ivansan.newsapp.ui.settings.SettingScreen
import com.ivansan.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    private lateinit var preferences: SharedPreferences

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE)
        setContent {
            NewsAppTheme {

                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        bottomBar = { NavigationBarM3(navController = navController) },
                        content = { paddingValues ->  MainNavHost(navController, paddingValues,preferences) }
                    )
                }

            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController, paddingValues: PaddingValues,preferences: SharedPreferences){

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController,
        startDestination = if (preferences.getString(PREFERENCES_API_KEY,null) == null) "settings" else "home",
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        composable("home"){

            HomeScreen(viewModel = hiltViewModel<HomeViewModel>(),navController,sharedViewModel,preferences)
        }
        composable("search"){

            SearchScreen(viewModel = hiltViewModel<SearchViewModel>(),navController,sharedViewModel)
        }
        composable("favorite"){
            FavoriteScreen(viewModel = hiltViewModel<FavoriteViewModel>(),navController,sharedViewModel)
        }
        composable("settings"){
            SettingScreen(preferences)
        }
        composable("news_page"){

            NewsPage(navController,sharedViewModel)

        }

    }
}


