package com.piyushprajpti.feature.library.due_and_fines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.library.components.FineSummaryCard
import com.piyushprajpti.feature.library.components.OverdueAlertCard
import com.piyushprajpti.feature.library.components.OverdueBookCard
import com.piyushprajpti.feature.library.components.RecentActivityCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LibraryDueScreen(
    onBackClick: () -> Unit,
    viewModel: LibraryDueViewModel = koinViewModel()
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
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "My Library Account"
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                top = 10.dp,
                bottom = 15.dp
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if (state.overdueBooks.isNotEmpty()) {
                item {
                    OverdueAlertCard(overdueCount = state.overdueBooks.size)
                }
            }

            item {
                FineSummaryCard(
                    totalFine = state.totalFine.toString(),
                    calculationInfo = state.calculationInfo,
                    lastUpdated = state.lastUpdated,
                    onPayFineClick = { viewModel.onEvent(LibraryDueEvent.PayFine) }
                )
            }

            if (state.overdueBooks.isNotEmpty()) {
                item {
                    SectionHeader(title = "Overdue Books")
                }
                items(state.overdueBooks) { book ->
                    OverdueBookCard(
                        title = book.title,
                        dueDate = book.dueDate,
                        overdueDays = book.overdueDays,
                        finePerDay = book.finePerDay,
                        totalFine = book.totalFine
                    )
                }
            }

            if (state.recentActivities.isNotEmpty()) {
                item {
                    SectionHeader(title = "Recent Activity")
                }
                items(state.recentActivities) { activity ->
                    RecentActivityCard(
                        title = activity.title,
                        status = activity.status,
                        date = activity.date
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                DownloadCertificateButton(
                    onClick = { viewModel.onEvent(LibraryDueEvent.DownloadCertificate) }
                )
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        color = CustomTheme.colors.primaryText,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 5.dp)
    )
}

@Composable
private fun DownloadCertificateButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Verified,
            contentDescription = null,
            tint = CustomTheme.colors.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            text = "Download Clearance Certificate",
            color = CustomTheme.colors.primary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
