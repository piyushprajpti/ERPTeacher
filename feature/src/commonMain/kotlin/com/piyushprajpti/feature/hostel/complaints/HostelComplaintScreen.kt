package com.piyushprajpti.feature.hostel.complaints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.piyushprajpti.feature.hostel.complaints.components.ComplaintSummaryCards
import com.piyushprajpti.feature.hostel.complaints.components.ComplaintTabs
import com.piyushprajpti.feature.hostel.complaints.components.GuidelineCard
import com.piyushprajpti.feature.hostel.complaints.components.HistoryCard
import com.piyushprajpti.feature.hostel.complaints.components.QuickReportCard
import com.piyushprajpti.feature.hostel.complaints.components.RecentActivityCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HostelComplaintScreen(
    onBackClick: () -> Unit,
    viewModel: HostelComplaintViewModel = koinViewModel()
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
            title = "Hostel Complaints"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                ComplaintTabs(
                    selectedTab = state.selectedTab,
                    onTabSelected = { viewModel.onEvent(HostelComplaintEvent.OnTabChange(it)) }
                )
            }

            when (state.selectedTab) {
                0 -> {
                    item {
                        ComplaintSummaryCards(
                            activeCount = state.activeCount,
                            resolvedCount = state.resolvedCount
                        )
                    }

                    item {
                        RecentActivityCard(
                            complaints = state.recentComplaints,
                            onRate = { id, rating -> viewModel.onEvent(HostelComplaintEvent.OnRateComplaint(id, rating)) }
                        )
                    }

                    item {
                        QuickReportCard(
                            categories = state.categories,
                            selectedCategory = state.selectedCategory,
                            onCategoryChange = { viewModel.onEvent(HostelComplaintEvent.OnCategoryChange(it)) },
                            description = state.description,
                            onDescriptionChange = { viewModel.onEvent(HostelComplaintEvent.OnDescriptionChange(it)) },
                            onUploadPhotoClick = { viewModel.onEvent(HostelComplaintEvent.OnUploadPhotoClick) },
                            onSubmitClick = { viewModel.onEvent(HostelComplaintEvent.OnSubmitComplaint) }
                        )
                    }
                }
                1 -> {
                    item {
                        GuidelineCard(guidelines = state.guidelines)
                    }
                }
                2 -> {
                    item {
                        HistoryCard(complaints = state.historyComplaints)
                    }
                }
            }
        }
    }
}
