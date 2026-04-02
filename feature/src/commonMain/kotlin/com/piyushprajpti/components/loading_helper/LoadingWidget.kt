package com.piyushprajpti.components.loading_helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun LoadingWidget(loading: Boolean = false) {
    if (loading) {
        Surface(
            modifier = Modifier.zIndex(99999f),
            color = Color(0x25000000)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(99999f)
                    .background(Color(0x25000000)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(55.dp)
                        .background(Color.White, CircleShape)
                        .padding(5.dp),
                    strokeWidth = 3.dp,
                    color = CustomTheme.colors.primary
                )
            }
        }
    }
}
