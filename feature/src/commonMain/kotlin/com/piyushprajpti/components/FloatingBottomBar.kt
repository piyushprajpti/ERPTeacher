package com.piyushprajpti.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.core.navigation.NavRoutes

val LocalBottomBarPadding = staticCompositionLocalOf<Dp> { 0.dp }

sealed class BottomBarTab(
    val route: NavRoutes,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarTab(
        route = NavRoutes.Home,
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Attendance : BottomBarTab(
        route = NavRoutes.Attendance,
        title = "Attendance",
        icon = Icons.AutoMirrored.Filled.MenuBook
    )

    data object Schedule : BottomBarTab(
        route = NavRoutes.Schedule,
        title = "Schedule",
        icon = Icons.Default.CalendarMonth
    )

    data object Settings : BottomBarTab(
        route = NavRoutes.Settings,
        title = "Settings",
        icon = Icons.Default.Settings
    )
}


@Composable
fun FloatingBottomBar(
    currentRoute: NavRoutes,
    onTabClick: (BottomBarTab) -> Unit,
    onHeightChanged: (Dp) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        BottomBarTab.Home,
        BottomBarTab.Attendance,
        BottomBarTab.Schedule,
        BottomBarTab.Settings
    )

    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val density = LocalDensity.current

    Row(
        modifier = modifier
            .onSizeChanged { size ->
                onHeightChanged(with(density) { size.height.toDp() })
            }
            .padding(top = 25.dp, bottom = 10.dp + navBarPadding, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .border(1.dp, CustomTheme.colors.outline, CircleShape)
            .background(CustomTheme.colors.secondaryBackground)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            val isSelected = currentRoute == tab.route
            BottomBarItem(
                tab = tab,
                isSelected = isSelected,
                onClick = { onTabClick(tab) }
            )
        }
    }
}

@Composable
private fun RowScope.BottomBarItem(
    tab: BottomBarTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) CustomTheme.colors.primary.copy(alpha = 0.15f) else Color.Transparent,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = tab.icon,
            contentDescription = tab.title,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = tab.title,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}