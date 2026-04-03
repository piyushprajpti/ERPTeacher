package com.piyushprajpti.feature.hostel.gate_pass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomTimePickerDialog
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.formatTime
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.hostel.gate_pass.components.ActivePassCard
import com.piyushprajpti.feature.hostel.gate_pass.components.GatePassTabs
import com.piyushprajpti.feature.hostel.gate_pass.components.HistoryItem
import com.piyushprajpti.feature.hostel.gate_pass.components.NewRequestCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HostelGatePassScreen(
    onBackClick: () -> Unit,
    viewModel: HostelGatePassViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    var showDeparturePicker by remember { mutableStateOf(false) }
    var showReturnPicker by remember { mutableStateOf(false) }

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    if (showDeparturePicker) {
        CustomTimePickerDialog(
            onTimeSelected = { h, m ->
                viewModel.onEvent(HostelGatePassEvent.OnDepartureTimeChange(formatTime(h, m)))
            },
            onDismiss = { showDeparturePicker = false }
        )
    }

    if (showReturnPicker) {
        CustomTimePickerDialog(
            onTimeSelected = { h, m ->
                viewModel.onEvent(HostelGatePassEvent.OnReturnTimeChange(formatTime(h, m)))
            },
            onDismiss = { showReturnPicker = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Gate Pass"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                GatePassTabs(
                    selectedTab = state.selectedTab,
                    onTabSelected = { viewModel.onEvent(HostelGatePassEvent.OnTabChange(it)) }
                )
            }

            if (state.selectedTab == 0) {
                // Apply Tab
                state.activePass?.let {
                    item {
                        ActivePassCard(pass = it)
                    }
                }

                item {
                    Text(
                        text = "NEW REQUEST",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }

                item {
                    NewRequestCard(
                        requestType = state.requestType,
                        onRequestTypeChange = { viewModel.onEvent(HostelGatePassEvent.OnRequestTypeChange(it)) },
                        reason = state.reason,
                        onReasonChange = { viewModel.onEvent(HostelGatePassEvent.OnReasonChange(it)) },
                        departureTime = state.departureTime,
                        onDepartureClick = { showDeparturePicker = true },
                        returnTime = state.returnTime,
                        onReturnClick = { showReturnPicker = true },
                        onSubmitClick = { viewModel.onEvent(HostelGatePassEvent.OnSubmitRequest) }
                    )
                }

                if (state.historyPasses.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "RECENT HISTORY",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.tertiaryText
                            )
                            Text(
                                text = "View All",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primary
                            )
                        }
                    }

                    items(state.historyPasses.take(2)) { pass ->
                        HistoryItem(
                            pass = pass,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                    }
                }
            } else {
                // History Tab
                items(state.historyPasses) { pass ->
                    HistoryItem(
                        pass = pass,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
            }
        }
    }
}
