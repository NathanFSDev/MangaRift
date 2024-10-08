package com.sephirita.mangarift.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sephirita.mangarift.presentation.component.banner.BannerPager
import com.sephirita.mangarift.presentation.component.list.horizontal.HorizontalMangaList
import com.sephirita.mangarift.presentation.component.load.Loader
import com.sephirita.mangarift.presentation.model.MangaListType
import com.sephirita.mangarift.presentation.model.StateAnimationType
import com.sephirita.mangarift.presentation.screen.destinations.DetailScreenDestination
import com.sephirita.mangarift.presentation.screen.destinations.SearchScreenDestination
import com.sephirita.mangarift.presentation.screen.error.ErrorScreen
import com.sephirita.mangarift.presentation.screen.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.homeState.collectAsState()

    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { viewModel.getMangas() }
    ) {
        with(state) {
            Scaffold(
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .navigationBarsPadding()
                    ) {
                        HorizontalDivider()
                    }
                }
            ) {
                Box(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 24.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(MangaListType.entries) { mangaType ->
                            when (mangaType) {
                                MangaListType.PopularNewTitles -> {
                                    BannerPager(
                                        items = state.popularNewTitles,
                                        detailNavigation = {
                                            navigator.navigate(DetailScreenDestination(it))
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }

                                MangaListType.Seasonal -> {
                                    val items = state.season
                                    HorizontalMangaList(
                                        listTitle = mangaType.title,
                                        items = items,
                                        detailNavigation = {
                                            navigator.navigate(DetailScreenDestination(it))
                                        },
                                        searchNavigation = {
                                            navigator.navigate(
                                                SearchScreenDestination(
                                                    initialSearch = mangaType
                                                )
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }

                                MangaListType.LatestUpdates -> {
                                    val items = state.latestUpdates
                                    HorizontalMangaList(
                                        listTitle = mangaType.title,
                                        items = items,
                                        detailNavigation = {
                                            navigator.navigate(DetailScreenDestination(it))
                                        },
                                        searchNavigation = {
                                            navigator.navigate(
                                                SearchScreenDestination(
                                                    initialSearch = mangaType
                                                )
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }

                                MangaListType.RecentlyAdded -> {
                                    val items = state.recentlyAdded
                                    HorizontalMangaList(
                                        listTitle = mangaType.title,
                                        items = items,
                                        detailNavigation = {
                                            navigator.navigate(DetailScreenDestination(it))
                                        },
                                        searchNavigation = {
                                            navigator.navigate(
                                                SearchScreenDestination(
                                                    initialSearch = mangaType
                                                )
                                            )
                                        }
                                    )
                                }
                            }

                        }
                    }
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .statusBarsPadding(),
                        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background),
                        onClick = { navigator.navigate(SearchScreenDestination()) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Screen Icon"
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isLoading || isError,
                enter = fadeIn(tween(300)),
                exit = fadeOut(tween(300)),
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    isLoading -> Loader(StateAnimationType.FLIPPING_PAGES)

                    isError -> {
                        ErrorScreen(
                            enabled = state.isError,
                            onBackPressed = { navigator.navigateUp() }
                        ) { viewModel.getMangas() }
                    }
                }
            }
        }
    }
}