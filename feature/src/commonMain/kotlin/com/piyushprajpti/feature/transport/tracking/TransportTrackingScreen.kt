package com.piyushprajpti.feature.transport.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.transport.tracking.components.DriverInfoCard
import com.piyushprajpti.feature.transport.tracking.components.TrackingMap
import com.piyushprajpti.feature.transport.tracking.components.TrackingStatsPill
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransportTrackingScreen(
    onBackClick: () -> Unit,
    viewModel: TransportTrackingViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LocationPermissionHandler(
        onResult = { viewModel.onEvent(TransportTrackingEvent.PermissionResult(it)) }
    )

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Google Maps Integration - Separated to prevent recomposition of entire screen
        TrackingMapLayer(
            state = state,
            viewModel = viewModel
        )

        // UI Layer - Overlay with header and controls
        TrackingUILayer(
            state = state,
            viewModel = viewModel,
            onBackClick = onBackClick
        )
    }
}

@Composable
expect fun LocationPermissionHandler(onResult: (Boolean) -> Unit)

@Composable
private fun TrackingMapLayer(
    state: TransportTrackingState,
    viewModel: TransportTrackingViewModel
) {
    // Memoized callbacks to prevent recreation
    val onZoomIn = remember { { viewModel.onEvent(TransportTrackingEvent.ZoomIn) } }
    val onZoomOut = remember { { viewModel.onEvent(TransportTrackingEvent.ZoomOut) } }
    val onMyLocation = remember { { viewModel.onEvent(TransportTrackingEvent.FocusMyLocation) } }

    TrackingMap(
        state = state,
        onZoomIn = onZoomIn,
        onZoomOut = onZoomOut,
        onMyLocation = onMyLocation
    )
}

@Composable
private fun TrackingUILayer(
    state: TransportTrackingState,
    viewModel: TransportTrackingViewModel,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header - Only recompose when routeName changes
        TrackingHeader(
            routeName = state.routeName,
            onBackClick = onBackClick
        )

        // Bottom Controls - Rest of the UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 20.dp)
        ) {
            // Map Control Buttons and Info - Aligned to bottom-right
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                // Map Zoom Controls
                MapControlButton(icon = Icons.Default.Add) { viewModel.onEvent(TransportTrackingEvent.ZoomIn) }
                MapControlButton(icon = Icons.Default.Remove) { viewModel.onEvent(TransportTrackingEvent.ZoomOut) }
                MapControlButton(icon = Icons.Default.MyLocation) { viewModel.onEvent(TransportTrackingEvent.FocusMyLocation) }
            }

            // Stats Pill and Driver Info - Aligned to bottom-center/left
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                // Stats Pill
                TrackingStatsPill(
                    distance = state.distance,
                    eta = state.eta
                )

                // Driver & Bus Details Card
                DriverInfoCard(
                    state = state,
                    onToggleAlert = { viewModel.onEvent(TransportTrackingEvent.ToggleAlert(it)) }
                )
            }
        }
    }
}

@Composable
private fun TrackingHeader(
    routeName: String,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            onBackClick = onBackClick,
            title = "Live Tracking - $routeName"
        )

        IconButton(
            onClick = { /* More options */ },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = CustomTheme.colors.primaryText
            )
        }
    }
}

@Composable
private fun MapControlButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.95f))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.primaryText,
            modifier = Modifier.size(24.dp)
        )
    }
}
