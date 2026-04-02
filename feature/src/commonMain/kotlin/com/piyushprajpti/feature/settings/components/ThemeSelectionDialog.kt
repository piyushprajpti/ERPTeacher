package com.piyushprajpti.feature.settings.components

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.settings.root.AppTheme

@Composable
fun ThemeSelectionDialog(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .widthIn(min = 280.dp)
                .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "Choose Theme",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppTheme.entries.forEach { theme ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onThemeSelected(theme)
                            onDismissRequest()
                        }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme == currentTheme,
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = CustomTheme.colors.primary,
                            unselectedColor = CustomTheme.colors.secondaryText
                        ),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = theme.label,
                        fontSize = 16.sp,
                        color = CustomTheme.colors.primaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Cancel",
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .padding(8.dp),
                    color = CustomTheme.colors.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
