package com.piyushprajpti.feature.settings.profile

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.college_erp_system.theme.LocalCustomColorScheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.settings.components.ProfilePhotoSection
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(title = "Student Profile", onBackClick = onBackClick)

        state.profileData?.let { profile ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp,
                    bottom = navBarPadding + 15.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    ProfilePhotoSection(
                        image = profile.profileImage,
                        name = profile.name,
                        enrollmentNo = profile.enrollmentNo,
                        department = profile.courseTag
                    )
                }

                section(
                    title = "Basic Information",
                    icon = Icons.Default.Person,
                    items = profile.basicInfo
                )

                section(
                    title = "Academic Details",
                    icon = Icons.Default.School,
                    items = profile.academicDetails
                )

                section(
                    title = "Contact Details",
                    icon = Icons.Default.Badge,
                    items = profile.contactDetails
                )

                section(
                    title = "Guardian Details",
                    icon = Icons.Default.Groups,
                    items = profile.guardianDetails
                )
            }
        }

    }
}

fun LazyGridScope.section(
    title: String,
    icon: ImageVector,
    items: List<ProfileItem>
) {
    item(span = { GridItemSpan(2) }) {
        SectionCard(title, icon, items)
    }
}

@Composable
fun SectionCard(
    title: String,
    icon: ImageVector,
    items: List<ProfileItem>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colors.secondaryBackground
        ),
        border = BorderStroke(1.dp, CustomTheme.colors.outline)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    color = CustomTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            val rows = mutableListOf<List<ProfileItem>>()
            var currentRow = mutableListOf<ProfileItem>()

            items.forEach { item ->
                if (item.isFullWidth) {
                    if (currentRow.isNotEmpty()) {
                        rows.add(currentRow)
                        currentRow = mutableListOf()
                    }
                    rows.add(listOf(item))
                } else {
                    currentRow.add(item)
                    if (currentRow.size == 2) {
                        rows.add(currentRow)
                        currentRow = mutableListOf()
                    }
                }
            }
            if (currentRow.isNotEmpty()) {
                rows.add(currentRow)
            }

            rows.forEachIndexed { index, rowItems ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    rowItems.forEachIndexed { itemIndex, profileItem ->
                        Box(modifier = Modifier.weight(1f)) {
                            ProfileItemView(profileItem)
                        }
                        if (itemIndex < rowItems.size - 1) {
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                    if (rowItems.size == 1 && !rowItems[0].isFullWidth) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                if (index < rows.size - 1) {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun ProfileItemView(item: ProfileItem) {
    val colors = LocalCustomColorScheme.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (item.icon != null) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colors.primaryBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (item.icon) {
                        ProfileIcon.Phone -> Icons.Default.Phone
                        ProfileIcon.Email -> Icons.Default.Email
                        ProfileIcon.Location -> Icons.Default.LocationOn
                    },
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
        Column {
            Text(
                text = item.label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = colors.secondaryText,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp
                )
            )
            Text(
                text = item.value,
                fontSize = 15.sp,
                color = colors.primaryText,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
