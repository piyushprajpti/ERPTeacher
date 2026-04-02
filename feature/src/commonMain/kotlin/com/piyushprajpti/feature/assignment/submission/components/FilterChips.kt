package com.piyushprajpti.feature.assignment.submission.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.assignment.submission.SubmissionFilter

@Composable
fun FilterChips(
    selectedFilter: SubmissionFilter,
    onFilterChange: (SubmissionFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(SubmissionFilter.entries) { filter ->
            val isSelected = filter == selectedFilter
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (isSelected) CustomTheme.colors.primary 
                        else CustomTheme.colors.outline.copy(alpha = 0.3f)
                    )
                    .clickable { onFilterChange(filter) }
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filter.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText
                )
            }
        }
    }
}
