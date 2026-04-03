package com.piyushprajpti.feature.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun FineSummaryCard(
    totalFine: String,
    calculationInfo: String,
    lastUpdated: String,
    onPayFineClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CustomTheme.colors.secondaryBackground,
                        CustomTheme.colors.secondaryBackground.copy(alpha = 0.8f)
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "TOTAL OUTSTANDING FINE",
                    color = CustomTheme.colors.tertiaryText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₹$totalFine",
                    color = CustomTheme.colors.primaryText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = null,
                tint = CustomTheme.colors.primaryText.copy(alpha = 0.2f),
                modifier = Modifier.size(60.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomTheme.colors.black.copy(alpha = 0.2f))
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = calculationInfo,
                    color = CustomTheme.colors.secondaryText,
                    fontSize = 12.sp
                )
                Text(
                    text = lastUpdated,
                    color = CustomTheme.colors.tertiaryText,
                    fontSize = 11.sp
                )
            }

            Button(
                onClick = onPayFineClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.primary
                ),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Pay Fine",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
