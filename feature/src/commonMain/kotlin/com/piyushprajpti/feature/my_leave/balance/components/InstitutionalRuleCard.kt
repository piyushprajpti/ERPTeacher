package com.piyushprajpti.feature.my_leave.balance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.my_leave.balance.InstitutionalRule

@Composable
fun InstitutionalRuleCard(
    rule: InstitutionalRule,
    onViewFullPolicyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Gavel,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = rule.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = rule.description,
                        fontSize = 14.sp,
                        color = CustomTheme.colors.secondaryText,
                        lineHeight = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Last Updated: ${rule.lastUpdated}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Row(
                    modifier = Modifier.clickable { onViewFullPolicyClick() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View Full Policy",
                        fontSize = 14.sp,
                        color = CustomTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
