package com.piyushprajpti.feature.result.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.result.GradeCount

@Composable
fun GradeDistributionCard(
    distribution: List<GradeCount>,
    modifier: Modifier = Modifier
) {
    val maxCount = distribution.maxOfOrNull { it.count } ?: 1
    var startAnimation by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(20.dp))
            .padding(15.dp)
    ) {
        Text(
            text = "Grade Distribution",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = CustomTheme.colors.primaryText
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            distribution.forEach { gradeCount ->
                val barHeightProgress by animateFloatAsState(
                    targetValue = if (startAnimation) gradeCount.count.toFloat() / maxCount else 0f,
                    animationSpec = tween(durationMillis = 1000)
                )
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight().weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(barHeightProgress)
                            .width(12.dp)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(
                                if (gradeCount.grade == "B") CustomTheme.colors.primary 
                                else CustomTheme.colors.primary.copy(alpha = 0.3f)
                            )
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            distribution.forEach { gradeCount ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = gradeCount.count.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (gradeCount.grade == "B") CustomTheme.colors.primary else CustomTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = gradeCount.grade,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.secondaryText
                    )
                }
            }
        }
    }
}
