package com.piyushprajpti.feature.leave.leave_apply

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Instant

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyLeaveApplyScreen(
    onBackClick: () -> Unit,
    viewModel: MyLeaveApplyViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    var showFromDatePicker by remember { mutableStateOf(false) }
    var showToDatePicker by remember { mutableStateOf(false) }

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    if (showFromDatePicker) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = state.fromDate)
        DatePickerDialog(
            onDismissRequest = { showFromDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(MyLeaveApplyEvent.OnFromDateChange(datePickerState.selectedDateMillis))
                    showFromDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showFromDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showToDatePicker) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = state.toDate)
        DatePickerDialog(
            onDismissRequest = { showToDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(MyLeaveApplyEvent.OnToDateChange(datePickerState.selectedDateMillis))
                    showToDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showToDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Apply Leave"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(15.dp)
        ) {
            Text(
                text = "LEAVE TYPE",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.leaveTypes.forEach { type ->
                    val isSelected = state.selectedLeaveType == type
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryBackground)
                            .clickable { viewModel.onEvent(MyLeaveApplyEvent.OnLeaveApplyTypeChange(type)) }
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = type,
                            color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.primaryText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DateField(
                    label = "FROM DATE",
                    date = state.fromDate,
                    onClick = { showFromDatePicker = true },
                    modifier = Modifier.weight(1f)
                )
                DateField(
                    label = "TO DATE",
                    date = state.toDate,
                    onClick = { showToDatePicker = true },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(20.dp))
                    .background(CustomTheme.colors.secondaryBackground)
                    .padding(horizontal = 15.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(CustomTheme.colors.primary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Total Days: ${state.totalDays}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "REASON FOR LEAVE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.secondaryText
                )
                Text(
                    text = "${state.reason.length}/300",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = state.reason,
                onValueChange = { viewModel.onEvent(MyLeaveApplyEvent.OnReasonChange(it)) },
                placeholder = "Explain the reason for your leave application...",
                singleLine = false,
                textFieldModifier = Modifier.height(120.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "EVIDENCE / ATTACHMENT",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(CustomTheme.colors.secondaryBackground)
                    .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(15.dp))
                    .clickable { /* Handle attachment */ },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(CustomTheme.colors.primaryBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null,
                            tint = CustomTheme.colors.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Upload medical certificate or letter",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = "PDF, JPG or PNG (Max 5MB)",
                        fontSize = 11.sp,
                        color = CustomTheme.colors.tertiaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = CustomTheme.colors.tertiaryText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "By submitting, you agree to the university leave policy guidelines.",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryActionButton(
                text = "Submit Application",
                onClick = { viewModel.onEvent(MyLeaveApplyEvent.OnSubmit) },
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Submit Application",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.white
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            tint = CustomTheme.colors.white,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
private fun DateField(
    label: String,
    date: Long?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.tertiaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .clickable(onClick = onClick)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = if (date != null) formatDate(date) else "Select Date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}

private fun formatDate(millis: Long): String {
    val date =
        Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault())
    val monthName = when (date.month) {
        Month.JANUARY -> "Jan"
        Month.FEBRUARY -> "Feb"
        Month.MARCH -> "Mar"
        Month.APRIL -> "Apr"
        Month.MAY -> "May"
        Month.JUNE -> "Jun"
        Month.JULY -> "Jul"
        Month.AUGUST -> "Aug"
        Month.SEPTEMBER -> "Sep"
        Month.OCTOBER -> "Oct"
        Month.NOVEMBER -> "Nov"
        Month.DECEMBER -> "Dec"
    }
    return "${date.dayOfMonth} $monthName ${date.year}"
}