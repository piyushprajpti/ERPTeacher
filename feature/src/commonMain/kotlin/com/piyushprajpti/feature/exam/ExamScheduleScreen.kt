package com.piyushprajpti.feature.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.ScrollableDate
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.exam.components.ExamDutyItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExamScheduleScreen(
    onBackClick: () -> Unit,
    viewModel: ExamScheduleViewModel = koinViewModel()
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
            title = "Exam Schedule"
        )

        ScrollableDate(
            selectedDate = state.selectedDate,
            startDate = state.startDate,
            endDate = state.endDate,
            onDateSelected = { viewModel.onEvent(ExamScheduleEvent.SelectDate(it)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                top = 24.dp,
                bottom = 15.dp
            )
        ) {
            items(state.examDuties) { duty ->
                ExamDutyItem(duty = duty)
            }
        }
    }
}
