package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piyushprajpti.feature.transport.tracking.CameraAction
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
expect fun TrackingMap(
    state: TransportTrackingState,
    cameraEvents: Flow<CameraAction> = emptyFlow(),
    onZoomIn: () -> Unit = {},
    onZoomOut: () -> Unit = {},
    onMyLocation: () -> Unit = {},
    modifier: Modifier = Modifier
)
