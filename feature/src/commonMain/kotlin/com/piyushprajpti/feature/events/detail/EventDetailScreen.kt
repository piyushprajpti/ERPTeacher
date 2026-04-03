package com.piyushprajpti.feature.events.detail

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.events.detail.components.CategoryChip
import com.piyushprajpti.feature.events.detail.components.DetailInfoCard
import com.piyushprajpti.feature.events.detail.components.RelatedEventItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventDetailScreen(
    onBackClick: () -> Unit,
    viewModel: EventDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
        // Custom Header for share button as per design
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = CustomTheme.colors.primaryText
                )
            }
            Text(
                text = "Event Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = CustomTheme.colors.primaryText
            )
            IconButton(onClick = { viewModel.onEvent(EventDetailEvent.OnShareClick) }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = CustomTheme.colors.primaryText
                )
            }
        }

        state.event?.let { event ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
                        AsyncImage(
                            model = event.bannerUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            CategoryChip(text = event.category, color = CustomTheme.colors.purple)
                            CategoryChip(text = event.organizedBy, color = CustomTheme.colors.primary)
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = event.title,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.primaryText
                        )
                        Spacer(modifier = Modifier.height(25.dp))

                        DetailInfoCard(
                            icon = Icons.Default.CalendarToday,
                            label = "DATE",
                            value = event.date
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DetailInfoCard(
                            icon = Icons.Default.Schedule,
                            label = "TIME",
                            value = event.time
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DetailInfoCard(
                            icon = Icons.Default.LocationOn,
                            label = "VENUE",
                            value = event.venue
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = "About Event",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.primaryText
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = event.description,
                            fontSize = 14.sp,
                            color = CustomTheme.colors.secondaryText,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = CustomTheme.colors.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = event.registeredCount,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))
                        PrimaryActionButton(
                            text = "Register Now",
                            onClick = { viewModel.onEvent(EventDetailEvent.OnRegisterClick) }
                        )
                    }
                }

                item {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Glimpses from 2025",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primaryText
                            )
                            Text(
                                text = "View All",
                                fontSize = 14.sp,
                                color = CustomTheme.colors.primary,
                                modifier = Modifier.clickable { viewModel.onEvent(EventDetailEvent.OnViewAllGlimpses) }
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            items(state.glimpses) { url ->
                                AsyncImage(
                                    model = url,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(width = 200.dp, height = 120.dp)
                                        .clip(RoundedCornerShape(15.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = "Related Events",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.primaryText
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                items(state.relatedEvents) { relatedEvent ->
                    RelatedEventItem(
                        event = relatedEvent,
                        onClick = { viewModel.onEvent(EventDetailEvent.OnRelatedEventClick(relatedEvent.id)) }
                    )
                }
            }
        }
    }
}
