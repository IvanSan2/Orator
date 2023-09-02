package com.ivansan.newsapp.ui.composable

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ivansan.newsapp.data.entity.Result
import com.ivansan.newsapp.ui.home.HomeViewModel
import com.ivansan.newsapp.ui.settings.PREFERENCES_LANGUAGE
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategoryTabRow(
    list: List<Result>,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel = hiltViewModel(),
    preferences: SharedPreferences,
) {

    val isSharedLanguage = !preferences.getString(PREFERENCES_LANGUAGE, null).isNullOrEmpty()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val topNews by if (isSharedLanguage) viewModel.topNewsByLanguage.observeAsState() else viewModel.topNews.observeAsState()
    val sportNews by if (isSharedLanguage) viewModel.sportNewsByLanguage.observeAsState() else viewModel.sportNews.observeAsState()
    val businessNews by if (isSharedLanguage) viewModel.businessNewsByLanguage.observeAsState() else viewModel.businessNews.observeAsState()
    val entertainmentNews by if (isSharedLanguage) viewModel.entertainmentNewsByLanguage.observeAsState() else viewModel.entertainmentNews.observeAsState()
    val healthNews by if (isSharedLanguage) viewModel.heathNewsByLanguage.observeAsState() else viewModel.heathNews.observeAsState()
    val technologyNews by if (isSharedLanguage) viewModel.technologyNewsByLanguage.observeAsState() else viewModel.technologyNews.observeAsState()
    val foodNews by if (isSharedLanguage) viewModel.foodNewsByLanguage.observeAsState() else viewModel.foodNews.observeAsState()
    val politicsNews by if (isSharedLanguage) viewModel.politicsNewsByLanguage.observeAsState() else viewModel.politicsNews.observeAsState()
    val worldNews by if (isSharedLanguage) viewModel.worldNewsByLanguage.observeAsState() else viewModel.worldNews.observeAsState()

    val allCategoryList = listOf<List<Result?>?>(
        list,
        topNews,
        sportNews,
        businessNews,
        entertainmentNews,
        healthNews,
        technologyNews,
        foodNews,
        politicsNews,
        worldNews)

    val titles = listOf(
        "All",
        "Top",
        "Sport",
        "Business",
        "Entertainment",
        "Health",
        "Technology",
        "Food",
        "Politics",
        "World"
    )


    Column() {

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = title)
                    }
                )
            }
        }

        HorizontalPager(state = pagerState, count = allCategoryList.size) {index ->
            LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
            ){
                itemsIndexed(allCategoryList[index]!!){_,item ->
                    NewsListItem(
                        item = item!!,
                        navController,
                        sharedViewModel
                    )
                }

            }
        }

    }


}