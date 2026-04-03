package com.piyushprajpti.feature.hostel.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.hostel.dashboard.components.HostelActionButtons
import com.piyushprajpti.feature.hostel.dashboard.components.MessMenuCard
import com.piyushprajpti.feature.hostel.dashboard.components.RecentComplaintsCard
import com.piyushprajpti.feature.hostel.dashboard.components.StayDetailsCard
import com.piyushprajpti.feature.hostel.dashboard.components.WardenContactCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HostelDashboardScreen(
    onBackClick: () -> Unit,
    onComplaintClick: () -> Unit,
    onGuestPassClick: () -> Unit,
    onMessClick: () -> Unit,
    viewModel: HostelDashboardViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Hostel Dashboard"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                HostelActionButtons(
                    onComplaintClick = onComplaintClick,
                    onGuestPassClick = onGuestPassClick,
                    onMessClick = onMessClick
                )
            }

            state.stayDetails?.let {
                item {
                    StayDetailsCard(stayDetails = it)
                }
            }

            if (state.messMenus.isNotEmpty()) {
                item {
                    MessMenuCard(menus = state.messMenus)
                }
            }

            if (state.recentComplaints.isNotEmpty()) {
                item {
                    RecentComplaintsCard(
                        complaints = state.recentComplaints,
                        onViewAllClick = onComplaintClick
                    )
                }
            }

            state.wardenDetails?.let {
                item {
                    WardenContactCard(
                        warden = it,
                        onCallClick = { phone ->
                            uriHandler.openUri("tel:$phone")
                        },
                        onMessageClick = { phone ->
                            uriHandler.openUri("sms:$phone")
                        }
                    )
                }
            }
        }
    }
}
