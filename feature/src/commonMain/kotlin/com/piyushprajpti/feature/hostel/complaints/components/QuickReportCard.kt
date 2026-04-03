package com.piyushprajpti.feature.hostel.complaints.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun QuickReportCard(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onUploadPhotoClick: () -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.AddCircleOutline,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Quick Report",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "CATEGORY",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.tertiaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.primaryBackground)
                .clickable { isExpanded = true }
                .padding(horizontal = 15.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCategory,
                    fontSize = 14.sp,
                    color = CustomTheme.colors.primaryText
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = CustomTheme.colors.tertiaryText
                )
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(CustomTheme.colors.secondaryBackground)
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { 
                            Text(
                                text = category, 
                                color = CustomTheme.colors.primaryText 
                            ) 
                        },
                        onClick = {
                            onCategoryChange(category)
                            isExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "DESCRIPTION",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.tertiaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.primaryBackground),
            placeholder = { 
                Text(
                    "Describe the issue in detail...", 
                    fontSize = 14.sp,
                    color = CustomTheme.colors.tertiaryText
                ) 
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = CustomTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = CustomTheme.colors.primaryText,
                unfocusedTextColor = CustomTheme.colors.primaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onUploadPhotoClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = CustomTheme.colors.primaryBackground),
            shape = RoundedCornerShape(15.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            border = null
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Upload Photo",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = CustomTheme.colors.primaryText
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = CustomTheme.colors.primary),
            shape = RoundedCornerShape(15.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = "Submit Complaint",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )
        }
    }
}
