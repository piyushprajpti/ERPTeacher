package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState

@Composable
actual fun TrackingMap(
    state: TransportTrackingState,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onMyLocation: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("Maps not implemented for iOS yet")
    }
}
