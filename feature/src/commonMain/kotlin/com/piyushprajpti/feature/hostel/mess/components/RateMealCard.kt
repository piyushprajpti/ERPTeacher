package com.piyushprajpti.feature.hostel.mess.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun RateMealCard(
    todayEmojiRating: Int, // 1: Bad, 2: OK, 3: Good
    onEmojiSelected: (Int) -> Unit,
    todayRating: Int,
    onRatingSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Rate Today's Meal",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            EmojiButton(
                emoji = "😫",
                label = "BAD",
                isSelected = todayEmojiRating == 1,
                onClick = { onEmojiSelected(1) }
            )
            EmojiButton(
                emoji = "😐",
                label = "OK",
                isSelected = todayEmojiRating == 2,
                onClick = { onEmojiSelected(2) }
            )
            EmojiButton(
                emoji = "😊",
                label = "GOOD",
                isSelected = todayEmojiRating == 3,
                onClick = { onEmojiSelected(3) }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            repeat(5) { index ->
                val isSelected = index < todayRating
                Icon(
                    imageVector = if (isSelected) Icons.Default.Star else Icons.Outlined.StarBorder,
                    contentDescription = null,
                    tint = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.tertiaryText,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onRatingSelected(index + 1) }
                )
                if (index < 4) Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun EmojiButton(
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = emoji,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .alpha(if (isSelected) 1f else 0.4f)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.tertiaryText
        )
    }
}
