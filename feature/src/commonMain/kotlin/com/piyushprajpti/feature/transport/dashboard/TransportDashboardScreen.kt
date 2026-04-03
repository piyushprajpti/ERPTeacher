package com.piyushprajpti.feature.transport.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.transport.dashboard.components.BusInfoCard
import com.piyushprajpti.feature.transport.dashboard.components.DriverCard
import com.piyushprajpti.feature.transport.dashboard.components.EtaCard
import com.piyushprajpti.feature.transport.dashboard.components.FeeSupportSection
import com.piyushprajpti.feature.transport.dashboard.components.MapPlaceholder
import com.piyushprajpti.feature.transport.dashboard.components.RouteTimeline
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransportDashboardScreen(
    onBackClick: () -> Unit,
    onTrackingClick: () -> Unit,
    viewModel: TransportDashboardViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Transport"
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                BusInfoCard(
                    busName = state.busName,
                    routeNumber = state.routeNumber,
                    busNumber = state.busNumber,
                    pickupPoint = state.pickupPoint,
                    scheduledTime = state.scheduledTime
                )
            }

            item {
                MapPlaceholder(currentLocation = state.currentPosition)
            }

            item {
                EtaCard(eta = state.eta)
            }

            item {
                RouteTimeline(timeline = state.timeline)
            }

            item {
                FeeSupportSection(
                    feeStatus = state.feeStatus,
                    validUntil = state.feeValidUntil,
                    onSupportClick = { viewModel.onEvent(TransportDashboardEvent.OnSupportClick) }
                )
            }

            item {
                DriverCard(
                    name = state.driverName,
                    image = state.driverImage,
                    onCallClick = {
                        if (state.driverPhone.isNotEmpty()) {
                            try {
                                uriHandler.openUri("tel:${state.driverPhone}")
                            } catch (e: Exception) {
                                viewModel.onEvent(TransportDashboardEvent.OnCallClick)
                            }
                        }
                    }
                )
            }

            item {
                PrimaryActionButton(
                    text = "Track My Bus Live",
                    onClick = onTrackingClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
