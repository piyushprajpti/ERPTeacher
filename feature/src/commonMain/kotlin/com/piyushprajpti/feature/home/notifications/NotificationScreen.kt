package com.piyushprajpti.feature.home.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.home.components.CategoryFilterRow
import com.piyushprajpti.feature.home.components.NotificationCard
import com.piyushprajpti.feature.home.components.UrgentHeader
import com.piyushprajpti.feature.home.components.UrgentNotificationCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()


    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(CustomTheme.colors.primaryBackground)
    ) {
        TopBar(
            title = "Notifications",
            onBackClick = onBackClick
        )

        CategoryFilterRow(
            categories = state.categories,
            selectedCategory = state.selectedCategory,
            onCategorySelected = { viewModel.onEvent(NotificationEvent.OnCategorySelected(it)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                top = 15.dp,
                bottom = navBarPadding + 15.dp
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            val urgentNotifications = state.notifications.filter { it.isUrgent }
            val recentNotifications = state.notifications.filter { !it.isUrgent }

            if (urgentNotifications.isNotEmpty()) {
                item {
                    UrgentHeader()
                }
                items(urgentNotifications) { notification ->
                    UrgentNotificationCard(
                        notification = notification,
                        onReadMoreClick = {
                            viewModel.onEvent(
                                NotificationEvent.OnNotificationClick(it)
                            )
                        }
                    )
                }
            }

            if (recentNotifications.isNotEmpty()) {
                item {
                    Text(
                        text = "RECENT NOTICES",
                        style = MaterialTheme.typography.labelMedium,
                        color = CustomTheme.colors.secondaryText,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(recentNotifications) { notification ->
                    NotificationCard(
                        notification = notification,
                        onClick = { viewModel.onEvent(NotificationEvent.OnNotificationClick(it)) }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = CustomTheme.colors.tertiaryText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SWIPE LEFT TO MARK AS READ",
                            style = MaterialTheme.typography.labelSmall,
                            color = CustomTheme.colors.tertiaryText,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }


}
