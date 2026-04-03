package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState

@Composable
expect fun TrackingMap(
    state: TransportTrackingState,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onMyLocation: () -> Unit,
    modifier: Modifier = Modifier
)
