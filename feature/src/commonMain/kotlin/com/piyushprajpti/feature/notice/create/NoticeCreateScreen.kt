package com.piyushprajpti.feature.notice.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomDatePickerDialog
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.CustomTimePickerDialog
import com.piyushprajpti.components.DropDown
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.formatMillisToDate
import com.piyushprajpti.components.formatTime
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.notice.create.components.AudienceChip
import com.piyushprajpti.feature.notice.create.components.PrioritySelector
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NoticeCreateScreen(
    onBackClick: () -> Unit,
    viewModel: NoticeCreateViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

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
            title = "Create New Notice",
            modifier = Modifier.padding(horizontal = 15.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Title
            CustomTextField(
                value = state.title,
                onValueChange = { viewModel.onEvent(NoticeCreateEvent.OnTitleChange(it)) },
                label = "TITLE",
                placeholder = "e.g. Guest Lecture on AI"
            )

            // Type
            Column {
                Text(
                    text = "Type",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                DropDown(
                    label = "",
                    itemList = state.types,
                    selectedItem = state.type,
                    itemToString = { it },
                    onItemSelected = { viewModel.onEvent(NoticeCreateEvent.OnTypeChange(it)) }
                )
            }

            // Priority
            PrioritySelector(
                selectedPriority = state.priority,
                onPrioritySelected = { viewModel.onEvent(NoticeCreateEvent.OnPriorityChange(it)) }
            )

            // Target Audience
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Target Audience",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = CustomTheme.colors.secondaryText
                    )
                    Text(
                        text = "+ Add More",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primary,
                        modifier = Modifier.clickable { viewModel.onEvent(NoticeCreateEvent.OnAddMoreAudience) }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.targetAudience.forEach { audience ->
                        AudienceChip(
                            text = audience,
                            onRemove = { viewModel.onEvent(NoticeCreateEvent.OnTargetAudienceRemove(audience)) }
                        )
                    }
                }
            }

            // Date
            CustomTextField(
                value = state.date,
                onValueChange = {},
                label = "Date",
                placeholder = "mm/dd/yyyy",
                enabled = false,
                textFieldModifier = Modifier.clickable { showDatePicker = true },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText
                    )
                }
            )

            // Time
            CustomTextField(
                value = state.time,
                onValueChange = {},
                label = "Time",
                placeholder = "--:-- --",
                enabled = false,
                textFieldModifier = Modifier.clickable { showTimePicker = true },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText
                    )
                }
            )

            // Venue
            CustomTextField(
                value = state.venue,
                onValueChange = { viewModel.onEvent(NoticeCreateEvent.OnVenueChange(it)) },
                label = "Venue",
                placeholder = "Main Hall",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText
                    )
                }
            )

            // Description
            Column {
                Text(
                    text = "Description",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(CustomTheme.colors.secondaryBackground)
                        .padding(15.dp)
                ) {
                    CustomTextField(
                        value = state.description,
                        onValueChange = { viewModel.onEvent(NoticeCreateEvent.OnDescriptionChange(it)) },
                        placeholder = "Add formatting...",
                        singleLine = false,
                        textFieldModifier = Modifier.height(120.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = CustomTheme.colors.outline)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Icon(Icons.Default.FormatBold, null, tint = CustomTheme.colors.secondaryText, modifier = Modifier.size(20.dp))
                        Icon(Icons.Default.FormatItalic, null, tint = CustomTheme.colors.secondaryText, modifier = Modifier.size(20.dp))
                        Icon(Icons.AutoMirrored.Filled.FormatListBulleted, null, tint = CustomTheme.colors.secondaryText, modifier = Modifier.size(20.dp))
                        Icon(Icons.Default.Link, null, tint = CustomTheme.colors.secondaryText, modifier = Modifier.size(20.dp))
                        Icon(Icons.Default.Image, null, tint = CustomTheme.colors.secondaryText, modifier = Modifier.size(20.dp))
                    }
                }
            }

            // Schedule Publish
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
                    .background(CustomTheme.colors.secondaryBackground)
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = "Schedule Publish",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = "Post automatically at a later time",
                        fontSize = 12.sp,
                        color = CustomTheme.colors.secondaryText
                    )
                }
                Switch(
                    checked = state.isScheduleEnabled,
                    onCheckedChange = { viewModel.onEvent(NoticeCreateEvent.OnScheduleToggle(it)) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = CustomTheme.colors.white,
                        checkedTrackColor = CustomTheme.colors.primary,
                        uncheckedThumbColor = CustomTheme.colors.secondaryText,
                        uncheckedTrackColor = CustomTheme.colors.outline
                    )
                )
            }
        }

        // Bottom Actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PrimaryActionButton(
                text = "Preview",
                onClick = { viewModel.onEvent(NoticeCreateEvent.OnPreview) },
                modifier = Modifier.weight(0.4f),
                containerColor = Color.Transparent,
                contentColor = CustomTheme.colors.primaryText,
                shape = RoundedCornerShape(25.dp),
                height = 50.dp
            )
            PrimaryActionButton(
                text = "Publish Now",
                onClick = { viewModel.onEvent(NoticeCreateEvent.OnPublish) },
                modifier = Modifier.weight(0.6f),
                height = 50.dp,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Publish Now",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.white
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            tint = CustomTheme.colors.white,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            )
        }
    }

    if (showDatePicker) {
        CustomDatePickerDialog(
            initialDateMillis = null,
            onDateSelected = { it?.let { viewModel.onEvent(NoticeCreateEvent.OnDateChange(formatMillisToDate(it))) } },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        CustomTimePickerDialog(
            onTimeSelected = { h, m -> viewModel.onEvent(NoticeCreateEvent.OnTimeChange(formatTime(h, m))) },
            onDismiss = { showTimePicker = false }
        )
    }
}