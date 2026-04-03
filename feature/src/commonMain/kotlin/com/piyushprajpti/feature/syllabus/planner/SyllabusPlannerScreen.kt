package com.piyushprajpti.feature.syllabus.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.DropDown
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.syllabus.planner.components.TopicCard
import com.piyushprajpti.feature.syllabus.planner.components.UnitProgressCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SyllabusPlannerScreen(
    onBackClick: () -> Unit,
    viewModel: SyllabusPlannerViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.primaryBackground)
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            TopBar(
                onBackClick = onBackClick,
                title = "Syllabus Planner",
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Current Subject Selector
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(CustomTheme.colors.secondaryBackground)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Current Subject",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DropDown(
                        label = "",
                        itemList = state.subjects,
                        selectedItem = state.selectedSubject,
                        itemToString = { it },
                        onItemSelected = { viewModel.onEvent(SyllabusPlannerEvent.OnSubjectChange(it)) }
                    )
                }

                // Unit Progress
                UnitProgressCard(unit = state.currentUnit)

                Text(
                    text = "CURRICULUM BREAKDOWN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.secondaryText
                )

                // Topics
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    state.curriculum.forEach { topic ->
                        TopicCard(
                            topic = topic,
                            onStatusChange = { viewModel.onEvent(SyllabusPlannerEvent.OnTopicStatusChange(topic.id, it)) },
                            onNotesChange = { viewModel.onEvent(SyllabusPlannerEvent.OnNotesChange(topic.id, it)) }
                        )
                    }
                }

                // Share Progress Switch
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
                            imageVector = Icons.Default.Groups,
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
                            text = "Share progress with students",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.primaryText
                        )
                        Text(
                            text = "Students will see covered topics in real-time",
                            fontSize = 12.sp,
                            color = CustomTheme.colors.secondaryText
                        )
                    }
                    Switch(
                        checked = state.isSharingEnabled,
                        onCheckedChange = { viewModel.onEvent(SyllabusPlannerEvent.OnSharingToggle(it)) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = CustomTheme.colors.white,
                            checkedTrackColor = CustomTheme.colors.primary,
                            uncheckedThumbColor = CustomTheme.colors.secondaryText,
                            uncheckedTrackColor = CustomTheme.colors.outline
                        )
                    )
                }
                
                Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
            }
        }

        // Save FAB
        FloatingActionButton(
            onClick = { viewModel.onEvent(SyllabusPlannerEvent.OnSave) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .navigationBarsPadding(),
            containerColor = CustomTheme.colors.primary,
            contentColor = CustomTheme.colors.white,
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
