package com.piyushprajpti.feature.attendance.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.attendance.dashboard.components.ActiveSessionCard
import com.piyushprajpti.feature.attendance.dashboard.components.ClassItemCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AttendanceDashboardScreen(
    onBackClick: () -> Unit,
    onCardClick: () -> Unit,
    viewModel: AttendanceDashboardViewModel = koinViewModel()
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
            title = "Attendance"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                Text(
                    text = "ACTIVE SESSION",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary.copy(alpha = 0.6f),
                    letterSpacing = 1.sp
                )
            }

            state.activeSession?.let { activeSession ->
                item {
                    ActiveSessionCard(
                        classItem = activeSession,
                        onClick = onCardClick
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Today's Classes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = state.currentDate,
                        fontSize = 12.sp,
                        color = CustomTheme.colors.secondaryText
                    )
                }
            }

            items(state.todayClasses) { classItem ->
                ClassItemCard(
                    classItem = classItem,
                    onClick = onCardClick
                )
            }
        }
    }
}
