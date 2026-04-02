package com.piyushprajpti.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun LogoBox(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(CustomTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isNullOrBlank()) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxSize()
                    .background(CustomTheme.colors.primary.copy(alpha = 0.2f))
                    .padding(12.dp),
                tint = CustomTheme.colors.primary
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
