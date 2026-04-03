package com.piyushprajpti.feature.library.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.library.components.IssuedBookCard
import com.piyushprajpti.feature.library.components.MyDueAndFinesButton
import com.piyushprajpti.feature.library.components.NewArrivalCard
import com.piyushprajpti.feature.library.components.PopularBookCard
import com.piyushprajpti.feature.library.components.SearchBar
import com.piyushprajpti.feature.library.components.SectionHeader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LibraryDashboardScreen(
    viewModel: LibraryDashboardViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookClick: () -> Unit,
    onDueClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Library Dashboard"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Search Bar
            item {
                SearchBar(
                    onClick = onSearchClick
                )
            }

            // Reading History
            item {
                MyDueAndFinesButton(
                    onClick = onDueClick
                )
            }

            // My Issued Books
            if (state.issuedBooks.isNotEmpty()) {
                item {
                    SectionHeader(
                        title = "My Issued Books",
                        actionText = "View All",
                        onActionClick = { viewModel.onEvent(LibraryDashboardEvent.OnViewAllIssuedBooksClick) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        contentPadding = PaddingValues(end = 15.dp)
                    ) {
                        items(state.issuedBooks) { book ->
                            IssuedBookCard(
                                book = book,
                                onRenewClick = { viewModel.onEvent(LibraryDashboardEvent.OnRenewBook(book.id)) },
                                onClick = { viewModel.onEvent(LibraryDashboardEvent.OnBookClick(book.id)) }
                            )
                        }
                    }
                }
            }

            // New Arrivals
            if (state.newArrivals.isNotEmpty()) {
                item {
                    SectionHeader(title = "New Arrivals")
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        contentPadding = PaddingValues(end = 15.dp)
                    ) {
                        items(state.newArrivals) { book ->
                            NewArrivalCard(
                                book = book,
                                onClick = onBookClick
                            )
                        }
                    }
                }
            }

            // Popular this month
            if (state.popularBooks.isNotEmpty()) {
                item {
                    SectionHeader(title = "Popular this month")
                }
                items(state.popularBooks) { book ->
                    PopularBookCard(
                        book = book,
                        onClick = { viewModel.onEvent(LibraryDashboardEvent.OnBookClick(book.id)) }
                    )
                }
            }
            
            // Extra spacing at the bottom
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
