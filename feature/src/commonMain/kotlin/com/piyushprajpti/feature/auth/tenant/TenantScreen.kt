package com.piyushprajpti.feature.auth.tenant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.SelectableCard
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TenantScreen(
    viewModel: TenantViewModel = koinViewModel(),
    onContinueClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    fun onContinueClick() {
        viewModel.onEvent(TenantEvent.OnContinueClick(onContinueClick))
    }

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
            .padding(15.dp)
    ) {

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Welcome Student",
            color = CustomTheme.colors.primaryText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Choose your university/college to continue",
            color = CustomTheme.colors.secondaryText,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        )

        Spacer(Modifier.height(32.dp))

        CustomTextField(
            placeholder = "Search Institutions",
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(TenantEvent.OnSearchQueryChange(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = CustomTheme.colors.secondaryText
                )
            },
            trailingIcon = {
                if (state.searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        tint = CustomTheme.colors.secondaryText,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                viewModel.onEvent(TenantEvent.OnSearchQueryChange(""))
                            }
                            .padding(5.dp)
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            if (state.isLoading) {
                item {
                    Spacer(Modifier.height(100.dp))
                    Text(
                        text = "Searching...",
                        color = CustomTheme.colors.secondaryText,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else if (state.filteredTenantList.isEmpty()) {
                item {
                    Spacer(Modifier.height(100.dp))
                    Text(
                        text = "No institutions found",
                        color = CustomTheme.colors.secondaryText,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                items(state.filteredTenantList) { tenant ->
                    SelectableCard(
                        title = tenant.name,
                        subtitle = tenant.address,
                        icon = tenant.logo,
                        isSelected = tenant.id == state.selectedTenant?.id,
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.onEvent(TenantEvent.OnTenantChange(tenant))
                        },
                    )
                }

            }
        }

        PrimaryActionButton(
            text = "Continue",
            onClick = {
                focusManager.clearFocus()
                onContinueClick()
            },
            isLoading = state.isLoading,
            enabled = state.selectedTenant != null && !state.isLoading,
        )
    }
}
