package com.piyushprajpti.feature.my_leave.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.my_leave.balance.components.InstitutionalRuleCard
import com.piyushprajpti.feature.my_leave.balance.components.LeaveBalanceCard
import com.piyushprajpti.feature.my_leave.balance.components.UpcomingLeaveCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyLeaveBalanceScreen(
    onBackClick: () -> Unit,
    onApplyClick: () -> Unit,
    viewModel: MyLeaveBalanceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current

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
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onBackClick = onBackClick,
                title = "My Leaves"
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Leave Balances Section
                item {
                    SectionHeader(title = "LEAVE BALANCES")
                }
                items(state.leaveBalances) { balance ->
                    LeaveBalanceCard(balance = balance)
                }

                // Upcoming Approved Section
                item {
                    SectionHeader(
                        title = "UPCOMING APPROVED",
                        actionText = "VIEW ALL",
                        onActionClick = { viewModel.onEvent(MyLeaveBalanceEvent.OnViewAllUpcomingClick) }
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        contentPadding = PaddingValues(end = 15.dp)
                    ) {
                        items(state.upcomingLeaves) { leave ->
                            UpcomingLeaveCard(leave = leave)
                        }
                    }
                }

                // Institutional Rules Section
                item {
                    SectionHeader(title = "INSTITUTIONAL RULES")
                }
                state.institutionalRules?.let { rules ->
                    item {
                        InstitutionalRuleCard(
                            rule = rules,
                            onViewFullPolicyClick = {
                                uriHandler.openUri("https://rafflesuniversity.edu.in/")
                            }
                        )
                    }
                }
                
                // Extra space at bottom for FAB
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Custom FAB
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primary)
                .clickable { onApplyClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Apply Leave",
                tint = CustomTheme.colors.white,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primary
        )
        if (actionText != null && onActionClick != null) {
            Text(
                text = actionText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary,
                modifier = Modifier.clickable { onActionClick() }
            )
        }
    }
}
