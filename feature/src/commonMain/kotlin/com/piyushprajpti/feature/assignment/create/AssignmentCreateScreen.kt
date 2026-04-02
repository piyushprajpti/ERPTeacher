package com.piyushprajpti.feature.assignment.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.automirrored.outlined.Rule
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.CustomDatePickerDialog
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.components.CustomTimePickerDialog
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.formatMillisToDate
import com.piyushprajpti.components.formatTime
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.assignment.create.components.BoxClickable
import com.piyushprajpti.feature.assignment.create.components.SectionHeader
import com.piyushprajpti.feature.assignment.create.components.SubmissionTypeItem
import com.piyushprajpti.feature.assignment.create.components.VisibilityToggleItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssignmentCreateScreen(
    onBackClick: () -> Unit,
    viewModel: AssignmentCreateViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Create Assignment"
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                SectionHeader(icon = Icons.Outlined.Edit, title = "General Information")
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(CustomTheme.colors.secondaryBackground)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    CustomTextField(
                        value = state.title,
                        onValueChange = { viewModel.onEvent(AssignmentCreateEvent.OnTitleChange(it)) },
                        label = "Assignment Title",
                        placeholder = "e.g. Binary Search Tree Implementation"
                    )

                    CustomTextField(
                        value = state.description,
                        onValueChange = { viewModel.onEvent(AssignmentCreateEvent.OnDescriptionChange(it)) },
                        label = "Description",
                        placeholder = "Provide detailed instructions for your students...",
                        singleLine = false,
                        textFieldModifier = Modifier.height(120.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        BoxClickable(
                            value = state.dueDate.ifEmpty { "mm/dd/yyyy" },
                            label = "Due Date",
                            onClick = { showDatePicker = true },
                            modifier = Modifier.weight(1f)
                        )
                        BoxClickable(
                            value = state.dueTime.ifEmpty { "--:-- --" },
                            label = "Due Time",
                            onClick = { showTimePicker = true },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    CustomTextField(
                        value = state.maxMarks,
                        onValueChange = { viewModel.onEvent(AssignmentCreateEvent.OnMaxMarksChange(it)) },
                        label = "Maximum Marks",
                        placeholder = "100",
                        trailingIcon = {
                            Text(
                                text = "POINTS",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primary,
                                modifier = Modifier.padding(end = 15.dp)
                            )
                        }
                    )
                }
            }

            item {
                SectionHeader(icon = Icons.AutoMirrored.Outlined.Rule, title = "Allowed Submissions")
                
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubmissionTypeItem(
                        icon = Icons.Default.PictureAsPdf,
                        label = "PDF",
                        isSelected = state.allowedSubmissions.contains(SubmissionType.PDF),
                        onClick = { viewModel.onEvent(AssignmentCreateEvent.OnSubmissionTypeToggle(SubmissionType.PDF)) },
                        modifier = Modifier.weight(1f)
                    )
                    SubmissionTypeItem(
                        icon = Icons.AutoMirrored.Filled.TextSnippet,
                        label = "TEXT",
                        isSelected = state.allowedSubmissions.contains(SubmissionType.TEXT),
                        onClick = { viewModel.onEvent(AssignmentCreateEvent.OnSubmissionTypeToggle(SubmissionType.TEXT)) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubmissionTypeItem(
                        icon = Icons.Default.Image,
                        label = "IMAGE",
                        isSelected = state.allowedSubmissions.contains(SubmissionType.IMAGE),
                        onClick = { viewModel.onEvent(AssignmentCreateEvent.OnSubmissionTypeToggle(SubmissionType.IMAGE)) },
                        modifier = Modifier.weight(1f)
                    )
                    SubmissionTypeItem(
                        icon = Icons.Default.Link,
                        label = "LINK",
                        isSelected = state.allowedSubmissions.contains(SubmissionType.LINK),
                        onClick = { viewModel.onEvent(AssignmentCreateEvent.OnSubmissionTypeToggle(SubmissionType.LINK)) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(CustomTheme.colors.secondaryBackground)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = "Reference Material",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clickable { viewModel.onEvent(AssignmentCreateEvent.OnAddReferenceMaterial) }
                            .dashedBorder(CustomTheme.colors.primary.copy(alpha = 0.5f), 15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Link,
                                contentDescription = null,
                                tint = CustomTheme.colors.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add reference PDF or Document",
                                fontSize = 13.sp,
                                color = CustomTheme.colors.secondaryText
                            )
                        }
                    }

                    state.referenceMaterials.forEach { material ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(CustomTheme.colors.primaryBackground)
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AttachFile,
                                contentDescription = null,
                                tint = CustomTheme.colors.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = material,
                                fontSize = 13.sp,
                                color = CustomTheme.colors.primaryText,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Publish Visibility",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primaryText
                            )
                            Text(
                                text = "Control when students see this",
                                fontSize = 12.sp,
                                color = CustomTheme.colors.secondaryText
                            )
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(CustomTheme.colors.primaryBackground)
                                .padding(4.dp)
                        ) {
                            VisibilityToggleItem(
                                text = "NOW",
                                isSelected = state.publishVisibility == PublishVisibility.NOW,
                                onClick = { viewModel.onEvent(AssignmentCreateEvent.OnPublishVisibilityChange(PublishVisibility.NOW)) }
                            )
                            VisibilityToggleItem(
                                text = "SCHEDULE",
                                isSelected = state.publishVisibility == PublishVisibility.SCHEDULE,
                                onClick = { viewModel.onEvent(AssignmentCreateEvent.OnPublishVisibilityChange(PublishVisibility.SCHEDULE)) }
                            )
                        }
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(10.dp))
                PrimaryActionButton(
                    text = "CREATE ASSIGNMENT",
                    onClick = { viewModel.onEvent(AssignmentCreateEvent.OnCreateAssignment) }
                )
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }

    if (showDatePicker) {
        CustomDatePickerDialog(
            initialDateMillis = null,
            onDateSelected = { millis ->
                millis?.let {
                    viewModel.onEvent(AssignmentCreateEvent.OnDueDateChange(formatMillisToDate(it)))
                }
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        CustomTimePickerDialog(
            onTimeSelected = { hour, minute ->
                viewModel.onEvent(AssignmentCreateEvent.OnDueTimeChange(formatTime(hour, minute)))
            },
            onDismiss = { showTimePicker = false }
        )
    }
}

fun Modifier.dashedBorder(color: Color, cornerRadius: Dp) = drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        ),
        cornerRadius = CornerRadius(cornerRadius.toPx())
    )
}